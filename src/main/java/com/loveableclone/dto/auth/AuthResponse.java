package com.loveableclone.dto.auth;

public record AuthResponse(
        String token,UserProfileResponse user) { //record = All properties
    // becomes private, final and immutable



}
