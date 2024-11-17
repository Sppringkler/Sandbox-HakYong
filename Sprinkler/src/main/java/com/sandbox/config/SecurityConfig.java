package com.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화 (API 서버 등에서 CSRF 비활성화)
                .csrf(csrf -> csrf.disable())

                // CORS 설정 (CorsConfigurer 사용)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("https://ssafysandbox.vercel.app");  // 허용할 origin
                    corsConfiguration.addAllowedMethod("GET");
                    corsConfiguration.addAllowedMethod("POST");
                    corsConfiguration.addAllowedMethod("PUT");
                    corsConfiguration.addAllowedMethod("DELETE");
                    corsConfiguration.addAllowedHeader("*");
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))

                // 요청 경로에 대한 접근 제어
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()  // 모든 경로에 대해 인증 없이 접근 허용
                );

        return http.build();
    }
}
