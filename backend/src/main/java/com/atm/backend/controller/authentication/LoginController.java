package com.atm.backend.controller.authentication;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.UserDto;

import com.atm.backend.business.services.IBaseService;
import com.atm.backend.config.security.TokenProvider;
import com.atm.backend.data.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private IBaseService baseService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto user) {

        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword());

            UserDto userDto = baseService
                    .entityToDto(userRepository.findUserByEmail(usernamePassword.getPrincipal().toString()));

            if (userDto == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Email is wrong!"));
            } else if (!passwordEncoder.matches(usernamePassword.getCredentials().toString(), userDto.getPassword())) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Password is wrong!"));
            } else {
                String accessToken = tokenProvider.generateAccessToken(userDto);
                return ResponseEntity.ok().body(Map.of("message", accessToken));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
