package com.atm.backend.business.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.atm.backend.business.dto.UserDto;

public interface ICustomerService {
    public Map<String, Object> getInfo(Long id);

    public float getAmountById(Long id);

    public ResponseEntity<?> depositById(Long id, Long deposit);

    public ResponseEntity<?> withdrawById(Long id, Long withdraw);

    public ResponseEntity<?> updateUserById(Long id, UserDto userDto);

}
