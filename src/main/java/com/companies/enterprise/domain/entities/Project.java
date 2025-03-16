package com.companies.enterprise.domain.entities;

import com.companies.enterprise.dtos.in.RequestProject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


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


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Project(RequestProject requestProject) {
        this.name = requestProject.name();
        this.cost = requestProject.cost();
        this.startDate = requestProject.startDate();
        this.endDate = requestProject.endDate();
        this.value = requestProject.value();
    }

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("A data de início deve ser anterior à data de término.");
        }
    }
}