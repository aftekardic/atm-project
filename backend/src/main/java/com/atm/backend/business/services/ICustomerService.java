package com.atm.backend.business.services;

import java.util.Map;

import com.atm.backend.business.dto.UserDto;

public interface ICustomerService {
    public Map<String, Object> getInfo(Long id);

    public float getAmountById(Long id);

    public float depositById(Long id, float deposit);

    public float withdrawById(Long id, float withdraw);

    public String updateUserById(Long id, UserDto userDto);

}
