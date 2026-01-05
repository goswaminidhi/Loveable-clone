package com.loveableclone.security;

import com.loveableclone.Repository.ProjectMemberRepository;
import com.loveableclone.enums.ProjectPermission;
import com.loveableclone.enums.ProjectRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpression {
    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    private boolean hasPermission(Long projectId, ProjectPermission projectPermission){
        Long userId = authUtil.getCurrentUserId();

        //We need only role, So it will fetch the role and map it
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId)
                .map(role -> role.getPermissions().contains(projectPermission))
                .orElse(false);
    }

    public boolean canViewProject(Long projectId) {
        return hasPermission(projectId,ProjectPermission.VIEW);
    }

    public boolean canEditProject(Long projectId) {
        return hasPermission(projectId,ProjectPermission.EDIT);
    }

    public boolean canDeleteProject(Long projectId) {
        return hasPermission(projectId,ProjectPermission.DELETE);
    }

    public boolean canViewMembers(Long projectId) {
        return hasPermission(projectId,ProjectPermission.VIEW_MEMBERS);
    }

    public boolean canManageMembers(Long projectId) {
        return hasPermission(projectId,ProjectPermission.MANAGE_MEMBERS);
    }
}
