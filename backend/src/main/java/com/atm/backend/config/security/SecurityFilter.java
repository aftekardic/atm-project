package com.atm.backend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.atm.backend.business.services.IBaseService;
import com.atm.backend.data.repository.UserRepository;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenProvider tokenService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IBaseService baseService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)

            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {

            var validationToken = tokenService.validateToken(token);
            var user = userRepository.findUserByEmail(validationToken);
            var authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        try {

            filterChain.doFilter(request, response);
        } catch (java.io.IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }

}