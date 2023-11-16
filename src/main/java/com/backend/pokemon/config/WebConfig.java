package com.backend.pokemon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Puedes restringir la ruta si quieres
                .allowedOrigins("http://localhost:3000") // La URL de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Los métodos que permites
                .allowedHeaders("*") // Puedes ser más específico en lugar de permitir todos los headers
                .allowCredentials(true);
    }
}
