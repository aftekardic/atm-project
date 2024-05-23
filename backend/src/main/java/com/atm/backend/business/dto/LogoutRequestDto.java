package com.atm.backend.business.dto;

public class LogoutRequestDto {

    private TokenDto tokenDto;
    private String email;

    // Getters and Setters
    public TokenDto getTokenDto() {
        return this.tokenDto;
    }

    public void setTokenDto(TokenDto tokenDto) {
        this.tokenDto = tokenDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
