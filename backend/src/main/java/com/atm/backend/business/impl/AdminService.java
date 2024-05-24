package com.atm.backend.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.services.IAdminService;
import com.atm.backend.business.services.IBaseService;
import com.atm.backend.data.entity.UserEntity;
import com.atm.backend.data.repository.RoleRepository;
import com.atm.backend.data.repository.UserRepository;

@Service
public class AdminService implements IAdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    IBaseService baseService;

    @Override
    public ResponseEntity<?> deleteUserById(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().body(Map.of("message", "User deleted succesfully!"));
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> updateUserById(Long id, UserDto userDto) {

        UserDto user = baseService.entityToDto(userRepository.findById(id).get());

        if (user != null) {
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setEmail(userDto.getEmail());
            user.setRole_id(userDto.getRole_id());
            RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findById(userDto.getRole_id()).get());
            userRepository.save(baseService.dtoToEntity(user, roleDto));
            return ResponseEntity.ok()
                    .body(Map.of("message", "Information was updated succesfully! Please log in again..."));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Information was not updated succesfully!"));
    }

}
