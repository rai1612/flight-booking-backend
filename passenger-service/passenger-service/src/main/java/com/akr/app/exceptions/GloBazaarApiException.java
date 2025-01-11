package com.akr.app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
/*
    it is a custom exception class to handle the exception
    when a valid user is not liked with a valid product in the table
 */
@Getter
public class GloBazaarApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public GloBazaarApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
