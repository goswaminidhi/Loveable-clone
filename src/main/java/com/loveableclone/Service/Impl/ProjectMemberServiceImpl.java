package com.loveableclone.Service.Impl;

import com.loveableclone.Service.ProjectMemberService;
import com.loveableclone.dto.member.InviteMemberRequest;
import com.loveableclone.dto.member.MemberResponse;
import com.loveableclone.dto.member.UpdateMemberRoleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse deleteProjectMemberRole(Long projectId, Long memberId, Long userId) {
        return null;
    }
}
