
package com.companies.enterprise.dto;

import com.companies.enterprise.entities.EmployeeProject;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeProjectDto {
    private Long id;
    private String role;
    private EmployeeDto employee;
    private ProjectDto project;

    public EmployeeProjectDto(EmployeeProject employeeProject) {
        this.id = employeeProject.getId();
        this.role = employeeProject.getRole();
        this.employee = new EmployeeDto(employeeProject.getEmployee());
        this.project = new ProjectDto(employeeProject.getProject());

    }


}
