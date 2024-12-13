package com.companies.enterprise.dto;

import com.companies.enterprise.entities.Employee;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupervisorDto {
    private UUID id;
    private String name;
    private String cpf;
    private String gender;

    public SupervisorDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.gender = employee.getGender();
    }
}
