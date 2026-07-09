package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.AuthResponse;
import com.example.coursemanagement.dto.LoginRequest;
import com.example.coursemanagement.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

   
    AuthResponse login(LoginRequest request);
}
