package com.companies.enterprise.dtos.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestAddress(

        long id,

        @NotBlank
        String city,
        @NotBlank
        String country,
        @NotBlank
        String fu,
        @NotNull
        Integer number,
        @NotBlank
        String postal_code,
        @NotBlank
        String street
) {


}
