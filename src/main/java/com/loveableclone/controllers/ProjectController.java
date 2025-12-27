package com.loveableclone.controllers;

import com.loveableclone.Service.ProjectService;
import com.loveableclone.dto.project.ProjectRequest;
import com.loveableclone.dto.project.ProjectResponse;
import com.loveableclone.dto.project.ProjectSummaryResponse;
import com.loveableclone.security.AuthUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;


    //Getting All Project
    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(){

        return ResponseEntity.ok(projectService.getUserProject());
    }


    //Getting single project by id
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){

        return ResponseEntity.ok(projectService.getUserProjectById(id));
    }


    //Creating the project
    @PostMapping()
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(request));

    }


    //Updating the project by id
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @RequestBody @Valid ProjectRequest request){
        return ResponseEntity.ok(projectService.updatedProject(id,request));
    }


    //Deleting the project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

