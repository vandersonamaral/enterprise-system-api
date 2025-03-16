package com.companies.enterprise.infrastructure.repositories;

import com.companies.enterprise.domain.entities.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
    List<EmployeeProject> findByEmployeeId(UUID employeeId);
}
