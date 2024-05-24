package com.atm.backend.business.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.atm.backend.business.dto.UserDto;
import com.atm.backend.data.entity.UserEntity;

public interface IAdminService {
    public List<UserEntity> getAllUser();

    public ResponseEntity<?> updateUserById(Long id, UserDto userDto);

    public ResponseEntity<?> deleteUserById(Long id);
}
