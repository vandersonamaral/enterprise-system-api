package com.companies.enterprise.entities;

import com.companies.enterprise.validation.RequestBudget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "budget")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double value;



    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Budget(RequestBudget requestBudget) {
        this.description=requestBudget.description();
        this.startDate=requestBudget.startDate();
        this.endDate=requestBudget.endDate();
        this.value=requestBudget.value();
    }


}
