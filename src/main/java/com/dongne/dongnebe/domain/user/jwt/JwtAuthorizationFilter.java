package com.dongne.dongnebe.domain.user.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Start JwtAuthorizationFilter");
        String jwtHeader = jwtTokenProvider.getTokenHeader(request);

        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String jwtToken = jwtTokenProvider.getTokenFromHeader(jwtHeader);
            String userId = jwtTokenProvider.verifyToken(jwtToken);

            if (userId != null) {
                User user = userRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User Id Not Found"));
                PrincipalDetails principalDetails = new PrincipalDetails(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

                /*SecurityContext에 직접 접근 후 세션 생성 -> 자동으로 UserDetailsService에 있는 loadByUsername 호출*/
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            createRequest(request, "Token Has Expired");
            throw e;
        }
    }

    private void createRequest(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
    }
}
