package com.example.catbackend.catbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



/**
 * Clase de configuración de seguridad de Spring Security.
 * Define las reglas de acceso, filtros y políticas de autenticación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtSecurity jwtSecurity;

    public SecurityConfig(JwtSecurity jwtSecurity) {
        this.jwtSecurity = jwtSecurity;
    }

    /**
     * Define la cadena de filtros de seguridad para las solicitudes HTTP.
     *
     * @param http objeto HttpSecurity para configurar la seguridad web
     * @return cadena de filtros configurada
     * @throws Exception si ocurre algún error de configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Desactiva CSRF para API stateless
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/auth/register", "/h2-console/**").permitAll() // Endpoints públicos
                .anyRequest().authenticated()) // El resto requiere autenticación
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No se guarda sesión en servidor
            .addFilterBefore(new JwtFilter(jwtSecurity),
                UsernamePasswordAuthenticationFilter.class); // Filtro personalizado antes del filtro de autenticación

        http.headers(headers -> headers
            .frameOptions(frame -> frame.disable())); // Permite uso de H2 Console

        return http.build();
    }

    /**
     * Bean para codificar contraseñas usando BCrypt.
     *
     * @return codificador de contraseñas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean para el AuthenticationManager, necesario para autenticación personalizada.
     *
     * @param config configuración de autenticación provista por Spring
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}