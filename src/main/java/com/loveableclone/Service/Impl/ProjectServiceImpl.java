package com.loveableclone.Service.Impl;

import com.loveableclone.Repository.ProjectMemberRepository;
import com.loveableclone.Repository.ProjectRepository;
import com.loveableclone.Repository.UserRepository;
import com.loveableclone.Service.ProjectService;
import com.loveableclone.dto.project.ProjectRequest;
import com.loveableclone.dto.project.ProjectResponse;
import com.loveableclone.dto.project.ProjectSummaryResponse;
import com.loveableclone.entity.Project;
import com.loveableclone.entity.ProjectMember;
import com.loveableclone.entity.ProjectMemberId;
import com.loveableclone.entity.User;
import com.loveableclone.enums.ProjectRole;
import com.loveableclone.error.ResourceNotFoundException;
import com.loveableclone.mapper.ProjectMapper;
import com.loveableclone.security.AuthUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {

        Long id = authUtil.getCurrentUserId();
//        User owner = userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User",id.toString())
//        );This is calling database and giving the whole user, but we don't need the whole user, we just need
//        reference to connect
        User owner = userRepository.getReferenceById(authUtil.getCurrentUserId());
        //If we ever want to get owner.getName() or anything else than it (owner) will call the database.

        
        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .project(project)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);


        //Converting Project entity -> project dto
        //Method 1 - set all properties manual
        //Method 2 - use model mapper, but it does not support record
        //it uses reflection which is slower
        //So here we will use map struct

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProject() {
        //Here we are getting all the projects of the user and also the project whose member the user is

        //This is 1 Method if doing this
//        return projectRepository.findByAllAccessibleByUser(userId)
//                .stream()
//                .map(project -> projectMapper.toProjectSummaryResponse(project))
//                .collect(Collectors.toList());

        //Another method
        Long userId = authUtil.getCurrentUserId();
        var projects = projectRepository.findByAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getUserProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project projects = getAccessibleProjectById(projectId, userId);
        return projectMapper.toProjectResponse(projects);
    }

    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updatedProject(Long projectId, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to update");
//        }

        project.setName(request.name());
        project = projectRepository.save(project); //This is not imp to write because we are using @Transactional
//        here. It will automatically save it.
        //If I am doing this then commit will only happen when whole block is successfully  executed .
        //If I want first execute this statement then go ahead.
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void softDelete(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to delete");
//        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    public Project getAccessibleProjectById(Long projectId,Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString()));

    }
}
