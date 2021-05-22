package com.devilpanda.auth_service.adapter.rest;

import com.devilpanda.auth_service.app.api.IncorrectEmailException;
import com.devilpanda.auth_service.app.api.UserAlreadyExistException;
import com.devilpanda.auth_service.app.api.UserNotFoundException;
import com.devilpanda.auth_service.app.api.WrongCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(UserNotFoundException e) {
        LOGGER.error(e.getMessage());
        LOGGER.trace(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException e) {
        LOGGER.error(e.getMessage());
        LOGGER.trace(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncorrectEmailException.class)
    public ResponseEntity<Object> handleIncorrectEmailException(IncorrectEmailException e) {
        LOGGER.error(e.getMessage());
        LOGGER.trace(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<Object> handleWrongCredentialsException(WrongCredentialsException e) {
        LOGGER.error(e.getMessage());
        LOGGER.trace(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}
