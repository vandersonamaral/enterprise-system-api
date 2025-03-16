package com.companies.enterprise.infrastructure.repositories;

import com.companies.enterprise.domain.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
