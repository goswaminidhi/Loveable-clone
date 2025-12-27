package com.loveableclone.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record JwtUserPrincipal(
        Long userId,
        String username,
        List<GrantedAuthority> authorities
){
    //instead of using user we are using this
    //Because we want to avoid unnecessary data call
}
