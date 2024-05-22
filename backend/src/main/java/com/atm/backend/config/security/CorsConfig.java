package com.atm.backend.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer{

    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000") // İzin verilen originler
        .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metotları
        .allowCredentials(true) // Credential'ların (örneğin, cookie'lerin) gönderilmesine izin ver
        .maxAge(3600); // Önbellek süresi
    }
    
}
