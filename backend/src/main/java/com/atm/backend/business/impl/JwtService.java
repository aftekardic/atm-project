package com.atm.backend.business.impl;

import com.atm.backend.business.services.IJwtSerice;

public class JwtService implements IJwtSerice {

    private String SECRET_KEY = "123";
    private Long EXPIRATION_TIME = (long) 1000;

    @Override
    public String generateToken(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getEmailFromToken(String token) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        // TODO Auto-generated method stub
        return false;
    }

}
