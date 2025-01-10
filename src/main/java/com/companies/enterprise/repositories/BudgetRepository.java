package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.Budget;
import com.companies.enterprise.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    @Query("SELECT b FROM Budget b WHERE b.department_id = :department AND b.startDate <= :date AND b.endDate >= :date")
    List<Budget> findValidBudgets(Department department, LocalDate date);
}
