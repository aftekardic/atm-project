package com.atm.backend.controller.authentication;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.UserDto;

import com.atm.backend.business.services.IBaseService;
import com.atm.backend.data.repository.RoleRepository;
import com.atm.backend.data.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IBaseService baseService;
    @Autowired
    RoleRepository roleRepository;

    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto user) {

        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email is already taken!"));
        }

        UserDto new_user = new UserDto();
        new_user.setName(user.getName());
        new_user.setSurname(user.getSurname());
        new_user.setEmail(user.getEmail());
        new_user.setPassword(passwordEncoder.encode(user.getPassword()));
        new_user.setAmount((long) 0);

        RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findByName("customer"));

        new_user.setRole_id(roleDto.getId());

        userRepository.save(baseService.dtoToEntity(new_user, roleDto));
        return ResponseEntity.ok().body(Map.of("message", "User registered successfully!"));

    }

}
