package com.companies.enterprise.controllers;

import com.companies.enterprise.services.DepartmentService;
import com.companies.enterprise.validation.RequestDepartment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/departmento")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping()
    public ResponseEntity getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity saveDepartment(@RequestBody @Valid RequestDepartment data) {
        return ResponseEntity.ok(departmentService.saveDepartment(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDepartment(@PathVariable Long id, @RequestBody @Valid RequestDepartment data) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.deleteDepartment(id));
    }
}
