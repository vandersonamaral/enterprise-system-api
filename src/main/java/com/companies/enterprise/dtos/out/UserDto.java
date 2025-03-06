package com.companies.enterprise.dtos.out;

import com.companies.enterprise.domain.enums.RoleEnum;


public record UserDto(
        String name,
        String login,
        String password,
        RoleEnum role
) {
}
