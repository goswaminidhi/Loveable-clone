package com.loveableclone.Service.Impl;

import com.loveableclone.Service.AuthService;
import com.loveableclone.dto.auth.AuthResponse;
import com.loveableclone.dto.auth.LoginRequest;
import com.loveableclone.dto.auth.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
