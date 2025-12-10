package com.loveableclone.controllers;

import com.loveableclone.Service.AuthService;
import com.loveableclone.Service.UserService;
import com.loveableclone.dto.auth.AuthResponse;
import com.loveableclone.dto.auth.LoginRequest;
import com.loveableclone.dto.auth.SignupRequest;
import com.loveableclone.dto.auth.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request){
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        //id will be provided by the spring.
        Long userId = 1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }
}
