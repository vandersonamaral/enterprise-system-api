package com.companies.enterprise.infrastructure.repositories;

import com.companies.enterprise.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AddressRepository extends JpaRepository<Address, Long> {

}
