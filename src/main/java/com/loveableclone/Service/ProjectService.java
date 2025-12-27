package com.loveableclone.Service;

import com.loveableclone.dto.project.ProjectRequest;
import com.loveableclone.dto.project.ProjectResponse;
import com.loveableclone.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
     List<ProjectSummaryResponse> getUserProject();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updatedProject(Long id, ProjectRequest request);

    void softDelete(Long id);

}
