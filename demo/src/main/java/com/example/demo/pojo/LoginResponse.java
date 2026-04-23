package com.example.demo.pojo;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private String message;

    public LoginResponse(String token, String username, String role, String message) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.message = message;
    }

    public LoginResponse(String message) {
        this.message = message;
    }
}