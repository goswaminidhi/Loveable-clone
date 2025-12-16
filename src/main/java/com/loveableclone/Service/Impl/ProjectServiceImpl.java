package com.loveableclone.Service.Impl;

import com.loveableclone.Repository.ProjectRepository;
import com.loveableclone.Repository.UserRepository;
import com.loveableclone.Service.ProjectService;
import com.loveableclone.dto.project.ProjectRequest;
import com.loveableclone.dto.project.ProjectResponse;
import com.loveableclone.dto.project.ProjectSummaryResponse;
import com.loveableclone.entity.Project;
import com.loveableclone.entity.User;
import com.loveableclone.mapper.ProjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long id) {
        User owner = userRepository.findById(id).orElseThrow();
        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();
        project = projectRepository.save(project);
        //Converting Project entity -> project dto
        //Method 1 - set all properties manual
        //Method 2 - use model mapper, but it does not support record
        //it uses reflection which is slower
        //So here we will use map struct

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProject(Long userId) {
        //Here we are getting all the projects of the user and also the project whose member the user is

        //This is 1 Method if doing this
//        return projectRepository.findByAllAccessibleByUser(userId)
//                .stream()
//                .map(project -> projectMapper.toProjectSummaryResponse(project))
//                .collect(Collectors.toList());

        //Another method
        var projects = projectRepository.findByAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project projects = getAccessibleProjectById(id,userId);
        return projectMapper.toProjectResponse(projects);
    }

    @Override
    public ProjectResponse updatedProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to update");
        }
        project.setName(request.name());
        project = projectRepository.save(project); //This is not imp to write because we are using @Transactional
//        here. It will automatically save it.
        //If I am doing this then commit will only happen when whole block is successfully  executed .
        //If I want first execute this statement then go ahead.
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to delete");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    public Project getAccessibleProjectById(Long projectId,Long userId){
        return projectRepository.findAccessibleProjectById(projectId,userId).orElseThrow();
    }
}
