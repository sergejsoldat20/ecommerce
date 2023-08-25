package com.example.adminapp.dto;

public class AuthResponse {
    public String token;

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}