package com.companies.enterprise.controllers;


import com.companies.enterprise.entities.Budget;
import com.companies.enterprise.repositories.BudgetRepository;
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
    BudgetRepository budgetRepository;

    @GetMapping
    public ResponseEntity findAllBudgets() {
        return ResponseEntity.ok(budgetRepository.findAll());
    }
    @PostMapping
    public ResponseEntity saveBudget(@RequestBody @Valid  RequestBudget data) {
        Budget budget = new Budget(data);
        budgetRepository.save(budget);
        return ResponseEntity.status(201).body("Orcamento efetuado com sucesso");
    }

    @PutMapping("/{id}")
    ResponseEntity updateBudget(@PathVariable long id,@RequestBody @Valid  RequestBudget data) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent()) {
            Budget budgetUpdate = budget.get();
            budgetUpdate.setDescription(data.description());
            budgetUpdate.setStartDate(data.startDate());
            budgetUpdate.setEndDate(data.endDate());
            budgetUpdate.setValue(data.value());
            budgetRepository.save(budgetUpdate);
            return ResponseEntity.ok("Orcamento atualizado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    ResponseEntity deleteBudget(@PathVariable long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent()) {
            budgetRepository.delete(budget.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
