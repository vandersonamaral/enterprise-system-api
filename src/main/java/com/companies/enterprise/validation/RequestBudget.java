package com.companies.enterprise.validation;

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
        Double value
) {
}
