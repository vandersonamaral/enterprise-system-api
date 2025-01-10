package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findBySupervisorId(Employee supervisorId);
}
