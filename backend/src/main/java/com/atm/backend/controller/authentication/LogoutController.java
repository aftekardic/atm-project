package com.atm.backend.controller.authentication;

import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.TokenDto;
import com.atm.backend.data.repository.TokenRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LogoutController {
    @Autowired
    TokenRepository tokenRepository;

    @PostMapping("/logOut")
    public ResponseEntity<?> Logout(@RequestBody TokenDto tokenDto) {
        tokenRepository.deleteById(tokenDto.getId());
        return ResponseEntity.ok().body(Map.of("message", "Logout successfully!"));
    }

}
