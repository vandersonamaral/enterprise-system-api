package com.companies.enterprise.controllers;

import com.companies.enterprise.entities.Department;
import com.companies.enterprise.entities.Project;
import com.companies.enterprise.repositories.DepartmentRepository;
import com.companies.enterprise.repositories.ProjectRepository;
import com.companies.enterprise.services.ProjectService;
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
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public ResponseEntity getAllProjects() {
        if (projectRepository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveProject(@RequestBody @Valid RequestProject data) {
        try {
            projectService.validateDepartment(data);

            Project project = new Project(data);
            Optional<Department> departmentOptional = departmentRepository.findById(data.department_id());
            if (departmentOptional.isPresent()) {
                project.setDepartment(departmentOptional.get());
            }
            projectRepository.save(project);
            return ResponseEntity.ok().body(project);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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
