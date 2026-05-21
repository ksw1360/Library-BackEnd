package com.example.Library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://main.d1t17ie3uipfsv.amplifyapp.com",
                        "https://www.ksw1360.asia",    // ← 추가!! (이게 진짜 핵심)
                        "https://ksw1360.asia"          // ← www 없는 버전도 추가
                        // "https://api.ksw1360.asia"  ← 이건 백엔드 자기 도메인이라 빼도 됨
                )
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}