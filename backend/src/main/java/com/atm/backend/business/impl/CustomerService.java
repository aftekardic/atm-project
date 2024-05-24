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

    @SuppressWarnings("unused")
    @Override
    public ResponseEntity<?> depositById(Long id, Long deposit) {
        UserDto user = baseService.entityToDto(userRepository.findById(id).get());

        Long currentAmount = user.getAmount();
        if (user != null) {
            user.setAmount(currentAmount + deposit);
            RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findById(user.getRole_id()).get());
            userRepository.save(baseService.dtoToEntity(user, roleDto));
            return ResponseEntity.ok()
                    .body(Map.of("amount", "Your funds deposit transfarred succesfully!"));
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of("amount", "Unreachable amount knowlage!"));
        }

    }

    @Override
    public float getAmountById(Long id) {
        return baseService.entityToDto(userRepository.findById(id).get()).getAmount();
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
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setEmail(userDto.getEmail());
            RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findById(userDto.getRole_id()).get());
            userRepository.save(baseService.dtoToEntity(user, roleDto));
            return ResponseEntity.ok()
                    .body(Map.of("message", "Information was updated succesfully! Please log in again..."));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Information was not updated succesfully!"));
    }

    @SuppressWarnings("unused")
    @Override
    public ResponseEntity<?> withdrawById(Long id, Long withdraw) {
        UserDto user = baseService.entityToDto(userRepository.findById(id).get());
        Long currentAmount = user.getAmount();
        if (user != null) {
            if (currentAmount > withdraw) {
                user.setAmount(currentAmount - withdraw);
                RoleDto roleDto = baseService.roleEntityToDto(roleRepository.findById(user.getRole_id()).get());
                userRepository.save(baseService.dtoToEntity(user, roleDto));
                return ResponseEntity.ok()
                        .body(Map.of("amount", "Your funds withdraw transfarred succesfully!"));
            } else {
                return ResponseEntity.badRequest()
                        .body(Map.of("amount", "You don't have enough funds!"));
            }
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of("amount", "Unreachable amount knowlage!"));
        }

    }

}
