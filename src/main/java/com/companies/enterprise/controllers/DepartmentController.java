package com.companies.enterprise.controllers;

import com.companies.enterprise.entities.Department;
import com.companies.enterprise.repositories.DepartmentRepository;
import com.companies.enterprise.validation.RequestDepartment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping()
    public ResponseEntity getAllDepartments() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveDepartment(@RequestBody @Valid RequestDepartment data) {
        Department department = new Department(data);
        departmentRepository.save(department);
        return ResponseEntity.status(201).body("Departamento salvo com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDepartment(@PathVariable Long id, @RequestBody @Valid RequestDepartment data) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            Department departmentUpdate = department.get();
            departmentUpdate.setName(data.name());
            departmentUpdate.setNumber(data.number());
            departmentRepository.save(departmentUpdate);
            return ResponseEntity.ok("Departamento atualizado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.notFound().build();
    }


}
