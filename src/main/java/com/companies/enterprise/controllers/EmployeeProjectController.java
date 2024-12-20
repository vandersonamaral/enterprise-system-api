package com.companies.enterprise.controllers;

import com.companies.enterprise.dto.EmployeeProjectDto;
import com.companies.enterprise.entities.Employee;
import com.companies.enterprise.entities.EmployeeProject;
import com.companies.enterprise.entities.Project;
import com.companies.enterprise.repositories.EmployeeProjectRepository;
import com.companies.enterprise.repositories.EmployeeRepository;
import com.companies.enterprise.repositories.ProjectRepository;
import com.companies.enterprise.services.EmployeeProjectsService;
import com.companies.enterprise.validation.RequestEmployeeProject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@RestController
@RequestMapping("/funcionario-projeto")
public class EmployeeProjectController {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    private EmployeeProjectsService employeeProjectsService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public ResponseEntity getAllEmployeeProjects() {
        return ResponseEntity.ok(employeeProjectRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveEmployeeProject(@RequestBody @Valid RequestEmployeeProject data) {
        try {
            employeeProjectsService.validateEmployeeProjects(data);
            EmployeeProject employeeProject = new EmployeeProject(data);

            Optional<Employee> employeeOptional = employeeRepository.findById(data.employee_id());
            if (employeeOptional.isPresent()) {
                employeeProject.setEmployee(employeeOptional.get());

            }
            Optional<Project> projectOptional = projectRepository.findById(data.project_id());
            if (projectOptional.isPresent()) {
                employeeProject.setProject(projectOptional.get());
            }

            employeeProjectRepository.save(employeeProject);
            return ResponseEntity.ok(new EmployeeProjectDto(employeeProject));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployeeProject(@PathVariable long id, @RequestBody @Valid RequestEmployeeProject data) {
        Optional<EmployeeProject> optionalEmployeeProject = employeeProjectRepository.findById((id));
        if (optionalEmployeeProject.isPresent()) {
            EmployeeProject employeeProject = optionalEmployeeProject.get();
            employeeProject.setRole(data.role());

            return ResponseEntity.ok(employeeProjectRepository.save(employeeProject));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeProject(@PathVariable long id) {
        Optional<EmployeeProject> optionalEmployeeProject = employeeProjectRepository.findById((id));
        if (optionalEmployeeProject.isPresent()) {
            employeeProjectRepository.delete(optionalEmployeeProject.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
