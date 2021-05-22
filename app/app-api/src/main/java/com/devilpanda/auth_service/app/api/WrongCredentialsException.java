package com.devilpanda.auth_service.app.api;

public class WrongCredentialsException extends RuntimeException {
    private static final String MESSAGE = "Wrong credentials for user -> email: %s, password: %s";

    // todo убрать password из логов
    public WrongCredentialsException(String email, String password) {
        super(String.format(MESSAGE, email, password));
    }
}
