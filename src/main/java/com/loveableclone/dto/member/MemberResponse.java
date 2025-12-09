package com.loveableclone.dto.member;

import com.loveableclone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long UserId,
        String email,
        String name,
        String avatarUrl,
        ProjectRole role,
        Instant invitedAt
) {
}
