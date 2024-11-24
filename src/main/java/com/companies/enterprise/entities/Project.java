package com.companies.enterprise.entities;

import com.companies.enterprise.validation.RequestProject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double cost;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double value;

    @OneToMany(mappedBy = "project")
    private List<EmployeeProject> employeesProject;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Project(RequestProject requestProject) {
        this.name=requestProject.name();
        this.cost=requestProject.cost();
        this.startDate=requestProject.startDate();
        this.endDate=requestProject.endDate();
        this.value=requestProject.value();
    }
}
