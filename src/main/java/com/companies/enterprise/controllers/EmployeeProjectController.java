package com.companies.enterprise.controllers;

import com.companies.enterprise.domain.entities.Project;
import com.companies.enterprise.domain.services.EmployeeProjectsService;
import com.companies.enterprise.dtos.in.RequestEmployeeProject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/funcionario-projeto")
public class EmployeeProjectController {


    @Autowired
    private EmployeeProjectsService employeeProjectsService;


    @GetMapping
    public ResponseEntity getAllEmployeeProjects() {
        return employeeProjectsService.getAllEmployeeProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Project>> getEmployeeProjectById(@PathVariable UUID id) {
        return employeeProjectsService.getEmployeeProjectById(id);
    }

    @PostMapping
    public ResponseEntity saveEmployeeProject(@RequestBody @Valid RequestEmployeeProject data) {
        return employeeProjectsService.saveEmployeeProject(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployeeProject(@PathVariable long id, @RequestBody @Valid RequestEmployeeProject data) {
        return employeeProjectsService.updateEmployeeProject(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeProject(@PathVariable long id) {
        return employeeProjectsService.deleteEmployeeProject(id);
    }
}