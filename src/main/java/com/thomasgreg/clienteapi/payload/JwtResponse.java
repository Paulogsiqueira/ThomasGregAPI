package com.thomasgreg.clienteapi.payload;

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter obrigatório
    public String getToken() {
        return token;
    }
}