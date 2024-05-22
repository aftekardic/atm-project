package com.atm.backend.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.dto.TokenDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.services.IBaseService;
import com.atm.backend.business.services.ITokenService;
import com.atm.backend.data.repository.TokenRepository;

@Service
public class TokenService implements ITokenService {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    IBaseService baseService;

    @Override
    public ResponseEntity<?> saveToken(String token, UserDto userDto, RoleDto roleDto) {
        TokenDto newToken = new TokenDto();

        newToken.setToken(token);
        newToken.setUser_id(userDto.getId());

        tokenRepository.save(baseService.tokenDtoToEntity(newToken, userDto, roleDto));

        return ResponseEntity.ok().build();
    }

    @Override
    public Long getTokenId(Long user_id) {
        return tokenRepository.findByUserId(user_id).getId();
    }

}
