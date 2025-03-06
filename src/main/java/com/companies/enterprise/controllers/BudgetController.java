package com.companies.enterprise.controllers;

import com.companies.enterprise.infrastructure.repositories.BudgetRepository;
import com.companies.enterprise.infrastructure.repositories.DepartmentRepository;
import com.companies.enterprise.domain.services.BudgetService;
import com.companies.enterprise.dtos.in.RequestBudget;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/orcamento")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ResponseEntity<?> findAllBudgets() {
        return budgetService.findAllBudgets();
    }

    @GetMapping("/status/{department_id}")
    public ResponseEntity<String> getBudgetStatus(@PathVariable Long department_id, @RequestParam LocalDate date) {
       return budgetService.getBudgetStatus(department_id, date);
    }

    @PostMapping
    public ResponseEntity saveBudget(@RequestBody @Valid RequestBudget data) {
        return budgetService.saveBudget(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable long id, @RequestBody @Valid RequestBudget data) {
        return budgetService.updateBudget(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable long id) {
       return budgetService.deleteBudget(id);
    }
}
