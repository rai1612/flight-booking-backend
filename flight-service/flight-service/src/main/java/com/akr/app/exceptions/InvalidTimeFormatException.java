package com.akr.app.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTimeFormatException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public InvalidTimeFormatException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
