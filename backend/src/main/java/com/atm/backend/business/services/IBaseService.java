package com.atm.backend.business.services;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.TokenDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.data.entity.RoleEntity;
import com.atm.backend.data.entity.TokenEntity;
import com.atm.backend.data.entity.UserEntity;

public interface IBaseService {
    public UserDto entityToDto(UserEntity userEntity);

    public UserEntity dtoToEntity(UserDto userDto, RoleDto roleDto);

    public RoleDto roleEntityToDto(RoleEntity roleEntity);

    public RoleEntity roleDtoToEntity(RoleDto roleDto);

    public TokenDto tokenEntityToDto(TokenEntity tokenEntity);

    public TokenEntity tokenDtoToEntity(TokenDto tokenDto, UserDto userDto, RoleDto roleDto);

}
