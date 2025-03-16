package com.companies.enterprise.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RoleEnum {

    ADMIN("admin"),
    USER("user");

    private String role;


}
