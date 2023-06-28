package com.dongne.dongnebe.domain.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dongne.dongnebe.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret.key}")
    private String secretKey;

    public static final String JWT_SUBJECT = "JWT";
    public static final int ACCESS_TOKEN_EXPIRATION_TIME = 2 * 60 * 60 * 1000;/*2 HOURS*/
    public static final int REFRESH_TOKEN_EXPIRATION_TIME = 5 * 24 * 60 * 60 * 1000; /*5 DAYS*/
    public static final String TOKEN_PREFIX = "Bearer ";

    public String responseRefreshToken(User user) {
        return createToken(user.getUserId(), REFRESH_TOKEN_EXPIRATION_TIME);
    }
    public String responseAccessToken(User user) {
        return createToken(user.getUserId(), ACCESS_TOKEN_EXPIRATION_TIME);
    }

    public String getAccessTokenHeader(HttpServletRequest request) {
        return request.getHeader("A-AUTH_TOKEN");
    }

    public String getRefreshTokenHeader(HttpServletRequest request) {
        return request.getHeader("R-AUTH_TOKEN");
    }

    private String createToken(String userId, int expirationTime) {
        return JWT.create()
                .withSubject(JWT_SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withClaim("userId", userId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public String verifyToken(String jwtToken) {
        return JWT.require(Algorithm.HMAC512(secretKey)).build().verify(jwtToken).getClaim("userId").asString();
    }

    public String getAccessTokenFromHeader(String jwtHeader) {
        return jwtHeader.replace(TOKEN_PREFIX, "");
    }
}
