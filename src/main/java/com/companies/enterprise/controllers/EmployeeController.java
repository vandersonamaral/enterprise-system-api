package com.companies.enterprise.controllers;


import com.companies.enterprise.dto.EmployeeDto;
import com.companies.enterprise.entities.Address;
import com.companies.enterprise.entities.Employee;
import com.companies.enterprise.exception.NoEmployeesFoundException;
import com.companies.enterprise.repositories.AddressRepository;
import com.companies.enterprise.repositories.EmployeeRepository;
import com.companies.enterprise.services.EmployeeService;
import com.companies.enterprise.validation.RequestEmployee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new NoEmployeesFoundException("Nao foi encontrado nenhum empregado");
        }
        return EmployeeDto.converter(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return ResponseEntity.ok(new EmployeeDto(employee.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByName(@PathVariable String name) {
        List<Employee> employee = employeeRepository.findByNameContainingIgnoreCase(name);

        if (employee.isEmpty()) {
            throw new NoEmployeesFoundException("Não foi encontrado nenhum funcionário com esse nome"+ name);
        }
        List<EmployeeDto> employeeDto = EmployeeDto.converter(employee);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<?> getEmployeesBySupervisor(@PathVariable UUID supervisorId) {
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

    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody @Valid RequestEmployee data) {
        try {
            employeeService.validateEmployeeData(data);

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

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@PathVariable UUID id, @RequestBody @Valid RequestEmployee data) {

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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
