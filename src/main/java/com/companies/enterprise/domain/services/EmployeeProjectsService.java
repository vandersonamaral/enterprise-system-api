package com.companies.enterprise.domain.services;

import com.companies.enterprise.dtos.out.EmployeeProjectDto;
import com.companies.enterprise.domain.entities.Employee;
import com.companies.enterprise.domain.entities.EmployeeProject;
import com.companies.enterprise.domain.entities.Project;
import com.companies.enterprise.infrastructure.repositories.EmployeeProjectRepository;
import com.companies.enterprise.infrastructure.repositories.EmployeeRepository;
import com.companies.enterprise.infrastructure.repositories.ProjectRepository;
import com.companies.enterprise.dtos.in.RequestEmployeeProject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeProjectsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    public ResponseEntity getAllEmployeeProjects() {
        if (employeeProjectRepository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employeeProjectRepository.findAll());

    }

    public ResponseEntity<List<Project>> getEmployeeProjectById(@PathVariable UUID id) {
        List<EmployeeProject> employeeProjects = employeeProjectRepository.findByEmployeeId(id);

        if (employeeProjects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Project> projects = employeeProjects.stream().map(EmployeeProject::getProject).collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }

    public ResponseEntity saveEmployeeProject(RequestEmployeeProject data) {
        try {
            validateEmployeeProjects(data);
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
            return ResponseEntity.status(HttpStatus.CREATED).body(new EmployeeProjectDto(employeeProject));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public ResponseEntity updateEmployeeProject(@PathVariable long id, @RequestBody @Valid RequestEmployeeProject data) {
        Optional<EmployeeProject> optionalEmployeeProject = employeeProjectRepository.findById((id));
        if (optionalEmployeeProject.isPresent()) {
            EmployeeProject employeeProject = optionalEmployeeProject.get();
            employeeProject.setRole(data.role());

            return ResponseEntity.ok(employeeProjectRepository.save(employeeProject));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deleteEmployeeProject(@PathVariable long id) {
        Optional<EmployeeProject> optionalEmployeeProject = employeeProjectRepository.findById((id));
        if (optionalEmployeeProject.isPresent()) {
            employeeProjectRepository.delete(optionalEmployeeProject.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public void validateEmployeeProjects(RequestEmployeeProject data) {
        Optional<Employee> employeeOptional = employeeRepository.findById(data.employee_id());
        if (employeeOptional.isEmpty()) {
            throw new IllegalArgumentException("Funcionario não encontrado");
        }
        Optional<Project> projectOptional = projectRepository.findById(data.project_id());
        if (projectOptional.isEmpty()) {
            throw new IllegalArgumentException("Projeto não encontrado");
        }
    }
}
