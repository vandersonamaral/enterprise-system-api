package com.companies.enterprise.repositories;

import com.companies.enterprise.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AddressRepository extends JpaRepository<Address, Long> {

}
