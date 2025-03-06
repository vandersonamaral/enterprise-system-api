package com.companies.enterprise.infrastructure.repositories;

import com.companies.enterprise.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findBySupervisorId(Employee supervisorId);
}
