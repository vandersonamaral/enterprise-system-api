package com.companies.enterprise.controllers;

import com.companies.enterprise.entities.Budget;
import com.companies.enterprise.entities.Department;
import com.companies.enterprise.repositories.BudgetRepository;
import com.companies.enterprise.repositories.DepartmentRepository;
import com.companies.enterprise.services.BudgetService;
import com.companies.enterprise.validation.RequestBudget;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        var budgets = budgetRepository.findAll();
        if (budgets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(budgets);
    }

    @PostMapping
    public ResponseEntity saveBudget(@RequestBody @Valid RequestBudget data) {
        try {
            budgetService.validateDepartment(data);

            Budget budget = new Budget(data);
            Optional<Department> departmentOptional = departmentRepository.findById(data.department_id());
            if (departmentOptional.isPresent()) {
                budget.setDepartment_id(departmentOptional.get());
            }
            budgetRepository.save(budget);
            return ResponseEntity.ok().body(budget);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable long id, @RequestBody @Valid RequestBudget data) {
        Optional<Budget> budgetOptional = budgetRepository.findById(id);

        if (budgetOptional.isPresent()) {
            Budget budget = budgetOptional.get();
            budget.setDescription(data.description());
            budget.setStartDate(data.startDate());
            budget.setEndDate(data.endDate());
            budget.setValue(data.value());
            budgetRepository.save(budget);
            return ResponseEntity.ok("Orçamento atualizado com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable long id) {
        Optional<Budget> budgetOptional = budgetRepository.findById(id);

        if (budgetOptional.isPresent()) {
            budgetRepository.delete(budgetOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
