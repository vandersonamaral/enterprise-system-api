package com.companies.enterprise.exception;

public class NoEmployeesFoundException extends RuntimeException {

    public NoEmployeesFoundException(String message) {
        super(message);
    }
}
