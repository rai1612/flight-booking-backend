package com.akr.app.exceptions;

public class DepartureBeforeArrivalException extends RuntimeException{
    public DepartureBeforeArrivalException() {
    }

    public DepartureBeforeArrivalException(String message) {
        super(message);
    }

    public DepartureBeforeArrivalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartureBeforeArrivalException(Throwable cause) {
        super(cause);
    }

    public DepartureBeforeArrivalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
