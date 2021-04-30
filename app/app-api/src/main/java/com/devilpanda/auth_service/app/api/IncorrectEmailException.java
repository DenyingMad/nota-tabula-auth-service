package com.devilpanda.auth_service.app.api;

public class IncorrectEmailException extends RuntimeException {
    public IncorrectEmailException() {
        super();
    }

    public IncorrectEmailException(String message) {
        super(message);
    }

    public IncorrectEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectEmailException(Throwable cause) {
        super(cause);
    }
}
