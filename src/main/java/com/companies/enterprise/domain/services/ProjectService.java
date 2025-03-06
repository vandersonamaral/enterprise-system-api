package com.companies.enterprise.domain.services;

import com.companies.enterprise.domain.entities.Department;
import com.companies.enterprise.domain.entities.Project;
import com.companies.enterprise.infrastructure.repositories.DepartmentRepository;
import com.companies.enterprise.infrastructure.repositories.ProjectRepository;
import com.companies.enterprise.dtos.in.RequestProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public ResponseEntity getAllProjects() {
        if (projectRepository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projectRepository.findAll());
    }

    public ResponseEntity saveProject(RequestProject data) {
        try {
            validateDepartment(data);

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

    public ResponseEntity updateProject(Long id, RequestProject data) {
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

    public ResponseEntity deleteProject(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.notFound().build();
    }

    private void validateDepartment(RequestProject data) {
        if (departmentRepository.findById(data.department_id()).isEmpty()) {
            throw new IllegalArgumentException("Departamento não encontrado");
        }
    }
}
