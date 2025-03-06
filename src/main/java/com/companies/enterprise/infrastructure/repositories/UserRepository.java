package com.companies.enterprise.infrastructure.repositories;

import com.companies.enterprise.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
