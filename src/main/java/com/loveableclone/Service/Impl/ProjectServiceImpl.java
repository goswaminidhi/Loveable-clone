package com.loveableclone.Service.Impl;

import com.loveableclone.Service.ProjectService;
import com.loveableclone.dto.project.ProjectRequest;
import com.loveableclone.dto.project.ProjectResponse;
import com.loveableclone.dto.project.ProjectSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public List<ProjectSummaryResponse> getUserProject(Long userId) {
        return List.of();
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public String deleteProject(Long id) {
        return "";
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long id) {
        return null;
    }

    @Override
    public ProjectResponse updatedProject(Long id, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public void softDelete(Long id, Long userId) {

    }
}
