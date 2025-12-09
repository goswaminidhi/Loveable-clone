package com.loveableclone.Service;

import com.loveableclone.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long id);
}
