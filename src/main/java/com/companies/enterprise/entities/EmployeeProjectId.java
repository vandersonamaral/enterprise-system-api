package com.companies.enterprise.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeProjectId {

    @Column(name = "employee_Id")
    private  Long employeeId;

    @Column(name = "project_Id")
    private  Long projectId;
}
