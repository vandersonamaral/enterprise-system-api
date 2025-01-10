package com.companies.enterprise.validation;

import com.companies.enterprise.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RequestEmployee(

        UUID id,

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
        UUID supervisorId,
        @NotNull
        Long address_id
) {
}
