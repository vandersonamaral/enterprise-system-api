package com.companies.enterprise.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestProject(
        long id,

        @NotBlank
        String name,
        @NotNull
        Double cost,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate,
        @NotNull
        Double value

) {
}