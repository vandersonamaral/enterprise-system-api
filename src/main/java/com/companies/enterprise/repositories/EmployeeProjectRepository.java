package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
}
