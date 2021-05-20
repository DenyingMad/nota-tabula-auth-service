package com.devilpanda.auth_service.app.api;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User with such credentials: %s not found";

    public UserNotFoundException(String credentials) {
        super(String.format(MESSAGE, credentials));
    }
}
