package com.companies.enterprise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "employee_project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;


    @EmbeddedId
    private EmployeeProjectId uuid;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_Id")
    private  Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_Id")
    private  Project project;


}
