package com.atm.backend.controller.authentication;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.TokenDto;
import com.atm.backend.business.dto.UserDto;

import com.atm.backend.business.services.IBaseService;
import com.atm.backend.business.services.ITokenService;
import com.atm.backend.config.security.TokenProvider;
import com.atm.backend.data.repository.RoleRepository;
import com.atm.backend.data.repository.TokenRepository;
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
    RoleRepository roleRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    private IBaseService baseService;
    @Autowired
    private ITokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/validateToken")
    public ResponseEntity<?> getValidateToken(@RequestBody TokenDto tokenObj) {

        TokenDto tokenDto = baseService.tokenEntityToDto(tokenRepository.findByUserId(tokenObj.getUser_id()));

        if (tokenDto != null
                && (tokenDto.getToken().contains(tokenObj.getToken())
                        || tokenObj.getToken().contains(tokenDto.getToken()))
                && tokenDto.getId() == tokenDto.getId()
                && tokenDto.getUser_id() == tokenObj.getUser_id()) {

            return ResponseEntity.ok().body(Map.of("message", "Token not null"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Token null"));

    }

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

                RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findById(userDto.getRole_id()).get());

                tokenService.saveToken(accessToken, userDto, roleDto);

                return ResponseEntity.ok()
                        .body(Map.of("tokenId", tokenService.getTokenId(userDto.getId()), "token", accessToken,
                                "user_id", userDto.getId()));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
