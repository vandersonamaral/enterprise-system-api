package com.companies.enterprise.dto;

import com.companies.enterprise.enums.RoleEnum;


public record UserDto(
        String name,
        String login,
        String password,
        RoleEnum role
) {
}
