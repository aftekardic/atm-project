package com.atm.backend.config.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

            throws ServletException, IOException, java.io.IOException {

        if ("/authenticate".equals(request.getServletPath()) || "/register".equals(request.getServletPath())
                || "/logOut".equals(request.getServletPath())
                || request.getServletPath().contains("/validateToken")
                || request.getServletPath().contains("/role")) {

            filterChain.doFilter(request, response);
            return;
        }
        var token = this.recoverToken(request);

        if (token != null) {

            var validationToken = tokenService.validateToken(token);
            var user = userRepository.findUserByEmail(validationToken);
            UserDetails userDetails = user; // Burada user bir UserDetails nesnesi olmalı
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            var authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                    authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid or missing token\"}");
            return;
        }
        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }

}
