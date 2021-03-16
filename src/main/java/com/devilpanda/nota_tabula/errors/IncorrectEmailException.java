package com.devilpanda.nota_tabula.errors;

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
