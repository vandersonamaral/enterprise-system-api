package com.companies.enterprise.controllers;

import com.companies.enterprise.dto.EmployeeDto;
import com.companies.enterprise.services.EmployeeService;
import com.companies.enterprise.validation.RequestEmployee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable UUID id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<?> getEmployeesBySupervisor(@PathVariable UUID supervisorId) {
        return employeeService.getEmployeesBySupervisor(supervisorId);
    }

    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody @Valid RequestEmployee data) {
        return employeeService.saveEmployee(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@PathVariable UUID id, @RequestBody @Valid RequestEmployee data) {
        return employeeService.updateEmployee(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable UUID id) {
        return employeeService.deleteEmployee(id);
    }
}