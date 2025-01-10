package com.companies.enterprise.services;

import com.companies.enterprise.entities.Budget;
import com.companies.enterprise.entities.Department;
import com.companies.enterprise.entities.Project;
import com.companies.enterprise.repositories.BudgetRepository;
import com.companies.enterprise.repositories.DepartmentRepository;
import com.companies.enterprise.repositories.ProjectRepository;
import com.companies.enterprise.validation.RequestBudget;
import org.springframework.beans.factory.annotation.Autowired;
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
