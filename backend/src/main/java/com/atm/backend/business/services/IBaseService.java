package com.atm.backend.business.services;

import com.atm.backend.business.dto.UserDto;
import com.atm.backend.data.entity.UserEntity;

public interface IBaseService {
    public UserDto entityToDto(UserEntity userEntity);

    public UserEntity dtoToEntity(UserDto userDto);
}
