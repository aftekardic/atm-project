package com.atm.backend.business.services;

import org.springframework.http.ResponseEntity;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.UserDto;

public interface ITokenService {
    public ResponseEntity<?> saveToken(String token, UserDto userDto, RoleDto roleDto);

    public Long getTokenId(Long user_id);

}
