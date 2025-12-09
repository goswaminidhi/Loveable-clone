package com.loveableclone.Service;

import com.loveableclone.dto.auth.AuthResponse;
import com.loveableclone.dto.auth.LoginRequest;
import com.loveableclone.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
