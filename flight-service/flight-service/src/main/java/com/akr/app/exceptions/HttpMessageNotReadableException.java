package com.akr.app.exceptions;

public class HttpMessageNotReadableException extends RuntimeException{
    public HttpMessageNotReadableException() {
    }

    public HttpMessageNotReadableException(String message) {
        super(message);
    }

    public HttpMessageNotReadableException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpMessageNotReadableException(Throwable cause) {
        super(cause);
    }

    public HttpMessageNotReadableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
