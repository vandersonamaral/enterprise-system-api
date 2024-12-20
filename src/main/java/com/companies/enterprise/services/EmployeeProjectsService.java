package com.companies.enterprise.services;

import com.companies.enterprise.entities.Employee;
import com.companies.enterprise.entities.Project;
import com.companies.enterprise.repositories.EmployeeRepository;
import com.companies.enterprise.repositories.ProjectRepository;
import com.companies.enterprise.validation.RequestEmployeeProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeProjectsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public void validateEmployeeProjects(RequestEmployeeProject data) {
        Optional<Employee> employeeOptional = employeeRepository.findById(data.employee_id());
        if (employeeOptional.isEmpty()) {
            throw new IllegalArgumentException("Funcionario não encontrado");
        }
        Optional<Project> projectOptional = projectRepository.findById(data.project_id());
        if (projectOptional.isEmpty()) {
            throw new IllegalArgumentException(" Projeto não encontrado");
        }
    }
}
