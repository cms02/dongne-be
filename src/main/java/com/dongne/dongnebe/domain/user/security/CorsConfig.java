package com.dongne.dongnebe.domain.user.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*"); /*허용할 헤더 목록 설정(Content-Type, Authorization 등)*/
        config.addAllowedOrigin("http://localhost:3000"); /*허용할 출처 목록 설정*/
        config.setAllowCredentials(true);/*자격 증명(쿠키, HTTP 인증 헤더 등) 사용 여부*/
        config.addAllowedMethod("*");/*허용할 HTTP 메서드 목록 설정*/
        config.setMaxAge(3600L);/*pre-flight 요청의 최대 유지 시간 설정*/

        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/login", config);
        return new CorsFilter(source);
    }
}
