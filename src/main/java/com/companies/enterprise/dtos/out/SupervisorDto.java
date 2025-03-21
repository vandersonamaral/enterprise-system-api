package com.companies.enterprise.dtos.out;

import com.companies.enterprise.domain.entities.Employee;
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
