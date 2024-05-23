package com.atm.backend.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.services.IBaseService;
import com.atm.backend.business.services.ICustomerService;
import com.atm.backend.data.repository.RoleRepository;
import com.atm.backend.data.repository.UserRepository;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IBaseService baseService;

    @Override
    public float depositById(Long id, float deposit) {

        return 0;
    }

    @Override
    public float getAmountById(Long id) {

        return 0;
    }

    @Override
    public Map<String, Object> getInfo(Long id) {
        UserDto userDto = baseService.entityToDto(userRepository.findById(id).get());

        RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findById(userDto.getRole_id()).get());

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", userDto.getName());
        userInfo.put("surname", userDto.getSurname());
        userInfo.put("email", userDto.getEmail());
        userInfo.put("password", userDto.getPassword());
        userInfo.put("role", roleDto.getName());
        return userInfo;

    }

    @Override
    public ResponseEntity<?> updateUserById(Long id, UserDto userDto) {

        UserDto user = baseService.entityToDto(userRepository.findById(id).get());
        if (user != null) {
            UserDto newUserDto = new UserDto();
            newUserDto.setId(user.getId());
            newUserDto.setName(userDto.getName());
            newUserDto.setSurname(userDto.getSurname());
            newUserDto.setEmail(userDto.getEmail());
            newUserDto.setPassword(userDto.getPassword());
            RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findById(user.getRole_id()).get());
            userRepository.save(baseService.dtoToEntity(newUserDto, roleDto));
            return ResponseEntity.ok()
                    .body(Map.of("message", "Information was updated succesfully! Please log in again..."));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Information was not updated succesfully!"));
    }

    @Override
    public float withdrawById(Long id, float withdraw) {

        return 0;
    }

}
