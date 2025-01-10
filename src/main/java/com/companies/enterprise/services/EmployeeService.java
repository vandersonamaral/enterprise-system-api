package com.companies.enterprise.services;

import com.companies.enterprise.entities.Address;
import com.companies.enterprise.repositories.AddressRepository;
import com.companies.enterprise.repositories.EmployeeRepository;
import com.companies.enterprise.validation.RequestEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void validateEmployeeData(RequestEmployee data) {
        Optional<Address> optionalAddress = addressRepository.findById(data.address_id());
        if (optionalAddress.isEmpty()) {
            throw new IllegalArgumentException("Endereço não encontrado");
        }

        if (data.supervisorId() != null && data.supervisorId().equals(data.id())) {
            throw new IllegalArgumentException("O funcionário não pode ser supervisor de si mesmo");
        }

        if (data.supervisorId() != null && employeeRepository.findById(data.supervisorId()).isEmpty()) {
            throw new IllegalArgumentException("Supervisor não encontrado");
        }
    }
}

