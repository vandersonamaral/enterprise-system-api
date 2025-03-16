package com.companies.enterprise.dtos.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestDepartment (

        long id,
        @NotBlank
        String name,
        @NotNull
        Integer number
){
}
