package com.companies.enterprise.entities;

import com.companies.enterprise.validation.RequestBudget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


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
    @JoinColumn
    private Department department_id;

    public Budget(RequestBudget requestBudget) {
        this.description = requestBudget.description();
        this.startDate = requestBudget.startDate();
        this.endDate = requestBudget.endDate();
        this.value = requestBudget.value();
    }

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("A data de início deve ser anterior à data de término.");
        }
    }
}
