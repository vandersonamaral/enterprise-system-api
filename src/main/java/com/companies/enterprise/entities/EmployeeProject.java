package com.companies.enterprise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "employee_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;


    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

}
