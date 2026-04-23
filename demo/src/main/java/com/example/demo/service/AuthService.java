package com.example.demo.service;

import com.example.demo.pojo.LoginRequest;
import com.example.demo.pojo.LoginResponse;
import com.example.demo.pojo.User;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void register(User user);
    boolean validatePassword(String rawPassword, String encodedPassword);
}