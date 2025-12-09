package com.loveableclone.Service;

import com.loveableclone.dto.project.ProjectRequest;
import com.loveableclone.dto.project.ProjectResponse;
import com.loveableclone.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
     List<ProjectSummaryResponse> getUserProject(Long userId);

    ProjectResponse getUserProjectById(Long id,Long userId);


    String deleteProject(Long id);

    ProjectResponse createProject(ProjectRequest request,Long id);

    ProjectResponse updatedProject(Long id, ProjectRequest request, Long userId);

    void softDelete(Long id, Long userId);

}
