package com.companies.enterprise.domain.services;

import com.companies.enterprise.dtos.out.EmployeeDto;
import com.companies.enterprise.domain.entities.Address;
import com.companies.enterprise.domain.entities.Employee;
import com.companies.enterprise.domain.exception.NoEmployeesFoundException;
import com.companies.enterprise.infrastructure.repositories.AddressRepository;
import com.companies.enterprise.infrastructure.repositories.EmployeeRepository;
import com.companies.enterprise.dtos.in.RequestEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new NoEmployeesFoundException("Nao foi encontrado nenhum empregado");
        }
        return EmployeeDto.converter(employees);
    }

    public ResponseEntity<EmployeeDto> getEmployeeById(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return ResponseEntity.ok(new EmployeeDto(employee.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<EmployeeDto>> getEmployeeByName(String name) {
        List<Employee> employee = employeeRepository.findByNameContainingIgnoreCase(name);

        if (employee.isEmpty()) {
            throw new NoEmployeesFoundException("Não foi encontrado nenhum funcionário com esse nome" + name);
        }
        List<EmployeeDto> employeeDto = EmployeeDto.converter(employee);
        return ResponseEntity.ok(employeeDto);
    }

    public ResponseEntity<?> getEmployeesBySupervisor(UUID supervisorId) {
        Optional<Employee> supervisorOpt = employeeRepository.findById(supervisorId);

        if (supervisorOpt.isPresent()) {
            Employee supervisor = supervisorOpt.get();
            List<Employee> employees = employeeRepository.findBySupervisorId(supervisor);

            if (!employees.isEmpty()) {
                return ResponseEntity.ok().body(employees);
            }
            return ResponseEntity.status(404).body("Nenhum funcionário foi encontrado sob a supervisão do supervisor com o ID: " + supervisorId);
        }
        return ResponseEntity.status(404).body("Supervisor não encontrado");
    }

    public ResponseEntity saveEmployee(RequestEmployee data) {
        try {
            validateEmployeeData(data);

            Employee employee = new Employee(data);

            Optional<Address> addressOpt = addressRepository.findById(data.address_id());
            if (addressOpt.isPresent()) {
                employee.setAddress_id(addressOpt.get());
            }

            if (data.supervisorId() != null) {
                Optional<Employee> supervisorOpt = employeeRepository.findById(data.supervisorId());
                if (supervisorOpt.isPresent()) {
                    employee.setSupervisorId(supervisorOpt.get());
                }
            }
            employeeRepository.save(employee);

            return ResponseEntity.status(201).body(new EmployeeDto(employee));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity updateEmployee(UUID id, RequestEmployee data) {

        Optional<Employee> optionalEmployee = employeeRepository.findById((id));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(data.name());
            employee.setCpf(data.cpf());
            employee.setGender(data.gender());
            employee.setBirthdate(data.birthdate());
            employee.setSalary(data.salary());

            return ResponseEntity.ok(employeeRepository.save(employee));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deleteEmployee(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}