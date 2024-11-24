package com.companies.enterprise.validation;

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
