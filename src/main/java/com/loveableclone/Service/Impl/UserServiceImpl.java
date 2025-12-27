package com.loveableclone.Service.Impl;

import com.loveableclone.Repository.UserRepository;
import com.loveableclone.Service.UserService;
import com.loveableclone.dto.auth.UserProfileResponse;
import com.loveableclone.error.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserProfileResponse getProfile(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User",username));
    }
}
