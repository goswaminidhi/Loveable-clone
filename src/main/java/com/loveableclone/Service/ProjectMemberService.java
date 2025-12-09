package com.loveableclone.Service;

import com.loveableclone.dto.member.InviteMemberRequest;
import com.loveableclone.dto.member.MemberResponse;
import com.loveableclone.dto.member.UpdateMemberRoleRequest;
import com.loveableclone.entity.ProjectMember;

import java.util.List;

public interface ProjectMemberService {

    List<ProjectMember> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);

    MemberResponse deleteProjectMemberRole(Long projectId, Long memberId, Long userId);
}
