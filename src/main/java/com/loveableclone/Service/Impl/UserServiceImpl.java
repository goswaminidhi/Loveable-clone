package com.loveableclone.Service.Impl;

import com.loveableclone.Service.UserService;
import com.loveableclone.dto.auth.UserProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long id) {
        return null;
    }
}
