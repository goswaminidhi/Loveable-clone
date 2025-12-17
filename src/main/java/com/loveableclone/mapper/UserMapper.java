package com.loveableclone.mapper;


import com.loveableclone.dto.auth.SignupRequest;
import com.loveableclone.dto.auth.UserProfileResponse;
import com.loveableclone.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

}
