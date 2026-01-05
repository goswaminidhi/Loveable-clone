package com.loveableclone.Service.Impl;

import com.loveableclone.Repository.ProjectMemberRepository;
import com.loveableclone.Repository.ProjectRepository;
import com.loveableclone.Repository.UserRepository;
import com.loveableclone.Service.ProjectMemberService;
import com.loveableclone.dto.member.InviteMemberRequest;
import com.loveableclone.dto.member.MemberResponse;
import com.loveableclone.dto.member.UpdateMemberRoleRequest;
import com.loveableclone.entity.Project;
import com.loveableclone.entity.ProjectMember;
import com.loveableclone.entity.ProjectMemberId;
import com.loveableclone.entity.User;
import com.loveableclone.mapper.ProjectMemberMapper;
import com.loveableclone.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberMapper projectMemberMapper;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        return projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList();
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to invite member.");
//        } //Handle it in authorization (security)


        User invitee = userRepository.findByUsername(request.username()).orElseThrow();//This is the email of the user which we
        // want to invite

        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if (projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("You are already invited.Cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);
        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to invite member.");
//        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());

        ProjectMember savedProjectMember = projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(savedProjectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void removeProjectMemberRole(Long projectId, Long memberId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("Not allowed.");
//        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if (!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Member not found in project");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
