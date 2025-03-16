package com.companies.enterprise.domain.services;

import com.companies.enterprise.domain.entities.Budget;
import com.companies.enterprise.domain.entities.Department;
import com.companies.enterprise.domain.entities.Project;
import com.companies.enterprise.infrastructure.repositories.BudgetRepository;
import com.companies.enterprise.infrastructure.repositories.DepartmentRepository;
import com.companies.enterprise.infrastructure.repositories.ProjectRepository;
import com.companies.enterprise.dtos.in.RequestBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public ResponseEntity<?> findAllBudgets() {
        var budgets = budgetRepository.findAll();
        if (budgets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(budgets);
    }

    public ResponseEntity<String> getBudgetStatus(Long department_id, LocalDate date) {

        Optional<Department> department = departmentRepository.findById(department_id);

        if (department.isEmpty()) {
            return ResponseEntity.badRequest().body("Departamento não encontrado");
        }

        String status = getBudgetStatus(department.get(), date);

        return ResponseEntity.ok(status);
    }

    public ResponseEntity saveBudget(RequestBudget data) {
        try {
            validateDepartment(data);

            Budget budget = new Budget(data);
            Optional<Department> departmentOptional = departmentRepository.findById(data.department_id());
            if (departmentOptional.isPresent()) {
                budget.setDepartment_id(departmentOptional.get());
            }
            budgetRepository.save(budget);
            return ResponseEntity.status(HttpStatus.CREATED).body(budget);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateBudget(long id, RequestBudget data) {
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

    public ResponseEntity<?> deleteBudget(long id) {
        Optional<Budget> budgetOptional = budgetRepository.findById(id);

        if (budgetOptional.isPresent()) {
            budgetRepository.delete(budgetOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    public void validateDepartment(RequestBudget data) {
        Optional<Department> optionalDepartment = departmentRepository.findById(data.department_id());
        if (optionalDepartment.isEmpty()) {
            throw new IllegalArgumentException("Departamento não encontrado");
        }
    }

    public String getBudgetStatus(Department department, LocalDate date) {

        List<Budget> validBudgets = budgetRepository.findValidBudgets(department, date);
        List<Project> validProjects = projectRepository.findValidProjects(department.getId(), date);

        if (validBudgets.isEmpty() && validProjects.isEmpty()) {
            return "Não há orçamentos ou projetos válidos para o departamento neste período.";
        }

        double totalBudget = validBudgets.stream()
                .mapToDouble(Budget::getValue)
                .sum();
        double totalProject = validProjects.stream()
                .mapToDouble(Project::getValue)
                .sum();

        if (totalProject <= totalBudget) {
            return "Verde";
        } else if (totalProject <= totalBudget * 1.10) {
            return "Amarelo";
        } else {
            return "Vermelho";
        }
    }
}
