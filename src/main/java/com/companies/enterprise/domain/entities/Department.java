package com.companies.enterprise.domain.entities;

import com.companies.enterprise.dtos.in.RequestDepartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "department")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer number;


    public Department(RequestDepartment requestDepartment) {
        this.name=requestDepartment.name();
        this.number=requestDepartment.number();
    }
}
