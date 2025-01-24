package com.companies.enterprise.services;

import com.companies.enterprise.entities.Department;
import com.companies.enterprise.repositories.DepartmentRepository;
import com.companies.enterprise.validation.RequestDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public ResponseEntity getAllDepartments() {
        if (departmentRepository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    public ResponseEntity saveDepartment(RequestDepartment data) {
        Department department = new Department(data);
        departmentRepository.save(department);
        return ResponseEntity.status(201).body("Departamento salvo com sucesso");
    }

    public ResponseEntity updateDepartment(Long id, RequestDepartment data) {
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

    public ResponseEntity deleteDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.notFound().build();
    }
}
