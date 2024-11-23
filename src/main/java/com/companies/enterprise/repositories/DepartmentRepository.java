package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
