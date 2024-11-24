package com.companies.enterprise.controllers;

import com.companies.enterprise.entities.Project;
import com.companies.enterprise.repositories.ProjectRepository;
import com.companies.enterprise.validation.RequestProject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projeto")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public ResponseEntity getAllProjects() {
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveProject(@RequestBody @Valid RequestProject data) {
        Project project = new Project(data);
        projectRepository.save(project);
        return ResponseEntity.status(201).body("Projeto salvo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProject(@PathVariable Long id, @RequestBody @Valid RequestProject data) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            Project projectUpdated = project.get();
            projectUpdated.setName(data.name());
            projectUpdated.setCost(data.cost());
            projectUpdated.setStartDate(data.startDate());
            projectUpdated.setEndDate(data.endDate());
            projectUpdated.setValue(data.value());
            projectRepository.save(projectUpdated);
            return ResponseEntity.ok("Projeto atualizado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.notFound().build();
    }
}
