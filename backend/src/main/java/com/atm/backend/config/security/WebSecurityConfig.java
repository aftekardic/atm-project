package com.atm.backend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(request -> request.getMethod().contains("POST")
                                && request.getServletPath().startsWith("/register"))
                        .permitAll()
                        .requestMatchers(request -> request.getMethod().contains("POST")
                                && request.getServletPath().startsWith("/authenticate"))
                        .permitAll()
                        // .requestMatchers(request -> request.getMethod().contains("POST")
                        // && request.getServletPath().startsWith("/v1/create"))
                        // .hasRole("ADMIN")
                        // .requestMatchers(request -> request.getMethod().contains("PUT")
                        // && request.getServletPath().startsWith("/v1/update"))
                        // .hasRole("ADMIN")
                        // .requestMatchers(request -> request.getMethod().contains("DELETE")
                        // && request.getServletPath().startsWith("/v1/delete"))
                        // .hasRole("ADMIN")
                        // .requestMatchers(request -> request.getMethod().contains("GET")
                        // && request.getServletPath().startsWith("/v1/all"))
                        // .hasRole("USER")

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}