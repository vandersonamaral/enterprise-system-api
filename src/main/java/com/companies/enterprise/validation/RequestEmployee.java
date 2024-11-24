package com.companies.enterprise.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestEmployee(

        long id,

        @NotBlank
        String name,
        @NotBlank
        String cpf,
        @NotBlank
        String gender,
        @NotNull
        LocalDate birthdate,
        @NotNull
        Double salary,
        @NotNull
        long address_id
) {
}
