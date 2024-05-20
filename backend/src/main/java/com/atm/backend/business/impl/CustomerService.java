package com.atm.backend.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.services.ICustomerService;
import com.atm.backend.data.repository.UserRepository;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    UserRepository userRepository;

    @Override
    public float depositById(Long id, float deposit) {

        return 0;
    }

    @Override
    public float getAmountById(Long id) {

        return 0;
    }

    @Override
    public UserDto getInfo(Long id) {

        return null;
    }

    @Override
    public String updateUserById(Long id, UserDto userDto) {

        return null;
    }

    @Override
    public float withdrawById(Long id, float withdraw) {

        return 0;
    }

}
