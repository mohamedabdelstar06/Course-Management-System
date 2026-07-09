package com.example.coursemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration — Phase 2 (JWT enforcement).
 *
 * - POST /api/auth/** is public (register + login).
 * - Every other endpoint requires a valid JWT Bearer token.
 * - CSRF is disabled (stateless REST API; no session cookies).
 *
 * The {@link JwtAuthenticationFilter} runs before Spring's default
 * UsernamePasswordAuthenticationFilter, validates the token, and
 * populates the SecurityContext so that downstream rules can enforce access.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * BCryptPasswordEncoder bean — injected wherever passwords need to be
     * hashed (AuthServiceImpl) or verified (login flow).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF — stateless REST API uses no session cookies
            .csrf(csrf -> csrf.disable())
            // Stateless session — no HttpSession created or used
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Auth endpoints are public — no token required
                .requestMatchers("/api/auth/**").permitAll()
                // Swagger UI is public
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // Uploads directory is public
                .requestMatchers("/uploads/**").permitAll()
                // All other endpoints require a valid JWT
                .anyRequest().authenticated()
            )
            // Wire the JWT filter before the default username/password filter
            .addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
