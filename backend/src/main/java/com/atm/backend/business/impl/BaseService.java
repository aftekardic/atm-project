package com.atm.backend.business.impl;

import org.springframework.stereotype.Service;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.TokenDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.services.IBaseService;
import com.atm.backend.data.entity.RoleEntity;
import com.atm.backend.data.entity.TokenEntity;
import com.atm.backend.data.entity.UserEntity;

@Service
public class BaseService implements IBaseService {

    @Override
    public UserEntity dtoToEntity(UserDto userDto, RoleDto roleDto) {

        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAmount(userDto.getAmount());
        userEntity.setRole(roleDtoToEntity(roleDto));
        return userEntity;
    }

    @Override
    public UserDto entityToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setSurname(userEntity.getSurname());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setAmount(userEntity.getAmount());
        userDto.setRole_id(userEntity.getRole().getId());
        return userDto;
    }

    @Override
    public RoleEntity roleDtoToEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDto.getId());
        roleEntity.setName(roleDto.getName());
        return roleEntity;
    }

    @Override
    public RoleDto roleEntityToDto(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleEntity.getId());
        roleDto.setName(roleEntity.getName());
        return roleDto;
    }

    @Override
    public TokenDto tokenEntityToDto(TokenEntity tokenEntity) {
        if (tokenEntity == null) {
            return null;
        }
        TokenDto tokenDto = new TokenDto();
        tokenDto.setId(tokenEntity.getId());
        tokenDto.setToken(tokenEntity.getToken());
        tokenDto.setUser_id(tokenEntity.getUser().getId());
        return tokenDto;
    }

    @Override
    public TokenEntity tokenDtoToEntity(TokenDto tokenDto, UserDto userDto, RoleDto roleDto) {
        if (tokenDto == null) {
            return null;
        }
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(tokenDto.getId());
        tokenEntity.setToken(tokenDto.getToken());
        tokenEntity.setUser(dtoToEntity(userDto, roleDto));

        return tokenEntity;
    }

}
