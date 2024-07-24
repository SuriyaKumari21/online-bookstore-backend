package com.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")  // Allow CORS requests to all endpoints
            .allowedOrigins("http://localhost:3000")  // Frontend origin
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
            .allowedHeaders("*")  // Allow all headers
            .allowCredentials(true);  // Allow credentials (cookies, authorization headers, etc.)
    }
}
