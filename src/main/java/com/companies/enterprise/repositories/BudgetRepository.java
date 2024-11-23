package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
