package com.atm.backend.business.services;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.data.entity.RoleEntity;
import com.atm.backend.data.entity.UserEntity;

public interface IBaseService {
    public UserDto entityToDto(UserEntity userEntity);

    public UserEntity dtoToEntity(UserDto userDto);

    public RoleDto roleEntityToDto(RoleEntity roleEntity);

    public RoleEntity roleDtoToEntity(RoleDto roleDto);

}
