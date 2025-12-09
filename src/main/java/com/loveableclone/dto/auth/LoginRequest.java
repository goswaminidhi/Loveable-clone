package com.loveableclone.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
