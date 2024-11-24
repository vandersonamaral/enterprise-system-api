package com.companies.enterprise.entities;

import com.companies.enterprise.validation.RequestDepartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Budget> budgets;

    public Department(RequestDepartment requestDepartment) {
        this.name=requestDepartment.name();
        this.number=requestDepartment.number();
    }
}
