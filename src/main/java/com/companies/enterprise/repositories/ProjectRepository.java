package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
