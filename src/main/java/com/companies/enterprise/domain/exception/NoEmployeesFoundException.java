package com.companies.enterprise.domain.exception;

public class NoEmployeesFoundException extends RuntimeException {

    public NoEmployeesFoundException(String message) {
        super(message);
    }
}
