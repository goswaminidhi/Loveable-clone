package com.loveableclone.Service.Impl;

import com.loveableclone.Repository.UserRepository;
import com.loveableclone.Service.AuthService;
import com.loveableclone.dto.auth.AuthResponse;
import com.loveableclone.dto.auth.LoginRequest;
import com.loveableclone.dto.auth.SignupRequest;
import com.loveableclone.entity.User;
import com.loveableclone.error.BadRequestException;
import com.loveableclone.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent( user -> {
               throw new BadRequestException("User already existed with username:"+ request.username());
        });

        User user = userMapper.toEntity(request);//In this we are storing the password in the String.
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);
        return new AuthResponse("dummy",userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
