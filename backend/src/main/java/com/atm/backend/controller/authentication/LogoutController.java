package com.atm.backend.controller.authentication;

import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.LogoutRequestDto;
import com.atm.backend.business.dto.TokenDto;
import com.atm.backend.data.repository.TokenRepository;
import com.atm.backend.data.repository.UserRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LogoutController {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/logOut")
    public ResponseEntity<?> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        TokenDto tokenDto = logoutRequestDto.getTokenDto();
        String email = logoutRequestDto.getEmail();

        if (tokenDto == null && email != null) {
            Long user_id = userRepository.findUserByEmail(email).getId();
            Long tokenId = tokenRepository.findByUserId(user_id).getId();
            tokenRepository.deleteById(tokenId);
            return ResponseEntity.ok().body(Map.of("message", "Logout successfully!"));
        } else if (tokenDto != null) {
            tokenRepository.deleteById(tokenDto.getId());
            return ResponseEntity.ok().body(Map.of("message", "Logout successfully!"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid request"));
        }
    }

}
