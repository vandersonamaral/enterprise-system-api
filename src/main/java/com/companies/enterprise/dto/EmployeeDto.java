package com.companies.enterprise.dto;

import com.companies.enterprise.entities.Address;
import com.companies.enterprise.entities.Employee;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private UUID id;
    private String name;
    private String cpf;
    private String gender;
    private LocalDate birthdate;
    private Double salary;

    private AddressDto address;
    private SupervisorDto supervisor;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.gender = employee.getGender();
        this.birthdate = employee.getBirthdate();
        this.salary = employee.getSalary();
        this.address = new AddressDto(employee.getAddress_id());

        // Apenas criar o SupervisorDto se o supervisor existir
        if (employee.getSupervisor_id() != null) {
            this.supervisor = new SupervisorDto(employee.getSupervisor_id());
        }
    }

    public static List<EmployeeDto> converter(List<Employee> employees) {
        return employees.stream().map(EmployeeDto::new).collect(Collectors.toList());
    }
}
