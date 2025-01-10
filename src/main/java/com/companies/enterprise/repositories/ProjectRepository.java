package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.department.id = :departmentId AND p.startDate <= :date AND p.endDate >= :date")
    List<Project> findValidProjects(Long departmentId, LocalDate date);
}
