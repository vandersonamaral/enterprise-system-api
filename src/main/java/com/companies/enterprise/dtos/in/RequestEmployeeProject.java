package com.companies.enterprise.dtos.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestEmployeeProject(

        long id,

        @NotBlank
        String role,

        @NotNull(message = "O campo 'employee_id' é obrigatório e não pode ser nulo.")
        UUID employee_id,

        @NotNull(message = "O campo 'project_id' é obrigatório e não pode ser nulo.")
        long project_id
) {
}
