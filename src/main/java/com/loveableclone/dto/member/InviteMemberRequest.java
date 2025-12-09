package com.loveableclone.dto.member;

import com.loveableclone.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
