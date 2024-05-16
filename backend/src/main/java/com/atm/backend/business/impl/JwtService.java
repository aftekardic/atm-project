package com.atm.backend.business.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.atm.backend.business.services.IJwtSerice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("deprecation")
@Service
public class JwtService implements IJwtSerice {

    private String SECRET_KEY = "123";
    private Long EXPIRATION_TIME = (long) 1000;

    @Override
    public String generateToken(String email) {
        Date now = new Date();
        Date expirDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder().setSubject(email).setIssuedAt(now).setExpiration(expirDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    @Override
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();

    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
