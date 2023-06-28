package com.dongne.dongnebe.domain.user.security;

import com.dongne.dongnebe.domain.user.jwt.JwtAuthorizationFilter;
import com.dongne.dongnebe.domain.user.jwt.JwtTokenProvider;
import com.dongne.dongnebe.domain.user.redis.RedisService;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(FormLoginConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable) /*httpBasic은 요청헤더의 Authorization key 에 id,pw를 그대로 노출*/
                .logout(LogoutConfigurer::disable)
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest.requestMatchers("/api/users/login", "/api/users/join").permitAll()
                                .anyRequest().authenticated())
                .cors(Customizer.withDefaults())
                .addFilterBefore(new JwtAuthorizationFilter(jwtTokenProvider, userRepository), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*"); /*허용할 헤더 목록 설정(Content-Type, Authorization 등)*/
        config.addAllowedOrigin("http://localhost:3000"); /*허용할 출처 목록 설정*/
        config.setAllowCredentials(true);/*자격 증명(쿠키, HTTP 인증 헤더 등) 사용 여부*/
        config.addAllowedMethod("*");/*허용할 HTTP 메서드 목록 설정*/
        config.setMaxAge(3600L);/*pre-flight 요청의 최대 유지 시간 설정*/
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}
