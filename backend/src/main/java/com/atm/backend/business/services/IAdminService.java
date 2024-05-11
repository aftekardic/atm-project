package com.atm.backend.business.services;

import java.util.ArrayList;

import com.atm.backend.business.dto.UserDto;

public interface IAdminService {
    public ArrayList<UserDto> getAllUser();

    public UserDto getUserById(Long id);

    public String addUser(UserDto userDto);

    public String updateUserById(Long id, UserDto userDto);

    public String deleteUserById(Long id);
}
