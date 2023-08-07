package com.dongne.dongnebe.domain.user.jwt;


import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Start JwtExceptionHandlerFilter");
        try {
            chain.doFilter(request, response);
        } catch (RuntimeException e) {
            String clientRequestUri = (String) request.getAttribute("clientRequestUri");
            String message = (String) request.getAttribute("message");
            responseExceptionMessage(response, clientRequestUri, message);
        }
    }

    private void responseExceptionMessage(HttpServletResponse response, String clientRequestUri, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        ResponseEntity<ResponseDto> errorResponse = ResponseEntity.ok(ResponseDto.builder()
                .responseMessage(message)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build());
        try {
            String result = objectMapper.writeValueAsString(errorResponse.getBody());
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
