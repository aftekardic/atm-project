package com.atm.backend.business.impl;

import org.springframework.stereotype.Service;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.services.IBaseService;
import com.atm.backend.data.entity.RoleEntity;
import com.atm.backend.data.entity.UserEntity;

@Service
public class BaseService implements IBaseService {

    @Override
    public UserEntity dtoToEntity(UserDto userDto) {

        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAmount(userDto.getAmount());

        return userEntity;
    }

    @Override
    public UserDto entityToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setName(userEntity.getName());
        userDto.setSurname(userEntity.getSurname());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());

        return userDto;
    }

    @Override
    public RoleEntity roleDtoToEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(roleDto.getName());
        return roleEntity;
    }

    @Override
    public RoleDto roleEntityToDto(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setName(roleEntity.getName());
        return roleDto;
    }

}
