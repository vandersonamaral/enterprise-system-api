package com.companies.enterprise.controllers;


import com.companies.enterprise.entities.Address;
import com.companies.enterprise.entities.Employee;
import com.companies.enterprise.repositories.AddressRepository;
import com.companies.enterprise.repositories.EmployeeRepository;
import com.companies.enterprise.validation.RequestEmployee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody @Valid RequestEmployee data) {
        Optional<Address> optionalAddress = addressRepository.findById(data.address_id());
        if (optionalAddress.isEmpty()) {
            return ResponseEntity.badRequest().body("Endereco nao encontrado pelo Id: " + data.address_id());
        }
        Address address = optionalAddress.get();
        Employee employee = new Employee(data);
        employee.setAddress(address);
        employeeRepository.save(employee);
        return ResponseEntity.ok("Funcionario salvo com Sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@PathVariable long id, @RequestBody @Valid RequestEmployee data) {

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
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
