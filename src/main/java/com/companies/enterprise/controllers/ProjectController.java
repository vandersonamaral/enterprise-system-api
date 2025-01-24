package com.companies.enterprise.controllers;

import com.companies.enterprise.services.ProjectService;
import com.companies.enterprise.validation.RequestProject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projeto")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @GetMapping
    public ResponseEntity getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ResponseEntity saveProject(@RequestBody @Valid RequestProject data) {
        return projectService.saveProject(data);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateProject(@PathVariable Long id, @RequestBody @Valid RequestProject data) {
        return projectService.updateProject(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

}
