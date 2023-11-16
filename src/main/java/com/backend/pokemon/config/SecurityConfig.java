package com.backend.pokemon.config;
/* 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // ... otras configuraciones de seguridad ...
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/users/createorlogin").permitAll() // Cambio aquí
                .anyRequest().authenticated())
            .httpBasic(withDefaults()); // Asegúrate de configurar la autenticación básica o JWT como necesites

        // Configuración CORS si es necesaria aquí
        http.cors(withDefaults());



        return http.build();
    }
} */