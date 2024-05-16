package com.atm.backend.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.UserDto;

import com.atm.backend.business.services.IBaseService;
import com.atm.backend.data.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegisterController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private IBaseService baseService;

    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto user) {

        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already taken!");
        }

        UserDto new_user = new UserDto();
        new_user.setName(user.getName());
        new_user.setSurname(user.getSurname());
        new_user.setEmail(user.getEmail());
        new_user.setPassword(passwordEncoder.encode(user.getPassword()));
        new_user.setAmount((long) 0);
        new_user.setRole_id((long) 1);

        userRepository.save(baseService.dtoToEntity(new_user));

        return ResponseEntity.ok("User registered successfully!");
    }

}
