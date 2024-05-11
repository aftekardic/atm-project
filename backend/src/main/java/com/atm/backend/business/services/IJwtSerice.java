package com.atm.backend.business.services;

public interface IJwtSerice {

    public String generateToken(String email);

    public String getEmailFromToken(String token);

    public boolean validateToken(String token);
}
