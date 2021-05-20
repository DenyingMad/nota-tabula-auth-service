package com.devilpanda.auth_service.app.api;

public class IncorrectEmailException extends RuntimeException {
    private static final String MESSAGE = "Email %s is not in the correct format";

    public IncorrectEmailException(String email) {
        super(String.format(MESSAGE, email));
    }
}
