package com.companies.enterprise.dtos.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestBudget(
        long id,

        @NotBlank
        String description,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate,
        @NotNull
        Double value,
        Long department_id
) {
}
