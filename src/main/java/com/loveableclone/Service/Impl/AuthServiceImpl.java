package com.loveableclone.Service.Impl;

import com.loveableclone.Repository.UserRepository;
import com.loveableclone.Service.AuthService;
import com.loveableclone.dto.auth.AuthResponse;
import com.loveableclone.dto.auth.LoginRequest;
import com.loveableclone.dto.auth.SignupRequest;
import com.loveableclone.entity.User;
import com.loveableclone.error.BadRequestException;
import com.loveableclone.mapper.UserMapper;
import com.loveableclone.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent( user -> {
               throw new BadRequestException("User already existed with username:"+ request.username());
        });

        User user = userMapper.toEntity(request);//In this we are storing the password in the String.
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        String token = authUtil.generateAccessToken(user);//Token is generated

        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(request.username(),request.password())
        );
        //Success authentication will give the authentication object else will throw error
        System.out.println("Authenticated?:"+authentication.isAuthenticated());
        User user = (User) authentication.getPrincipal();//here user is inside  this principle
        //getPrincipal() =  Stores the logged-in user object inside principal
        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }
}
