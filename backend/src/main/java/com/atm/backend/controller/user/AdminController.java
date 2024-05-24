package com.atm.backend.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.services.IAdminService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok().body(Map.of("users", adminService.getAllUser()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserInformationsById(@PathVariable Long id, @RequestBody UserDto userDto) {
        adminService.updateUserById(id, userDto);
        return ResponseEntity.ok().body(Map.of("message", "User was updated succesfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        adminService.deleteUserById(id);
        return ResponseEntity.ok().body(Map.of("message", "User was deleted succesfully!"));
    }
}