package com.akr.app.exceptions;


import com.akr.app.dtos.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/*
     * it is a centralized exception handler class to handle the exceptions
     * use this as utility while building the business logic in service layer
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    // it will handle the exception for invalid user or product ID
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest webRequest
    ) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /* It will handle the exception when the user id is not linked
       with the product id in the table
    */
    @ExceptionHandler(GloBazaarApiException.class)
    public ResponseEntity<ErrorResponse> handleGloBazaarApiException(
            GloBazaarApiException ex,
            WebRequest webRequest
    ) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                new Date(),
                "Invalid date/time format.",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DepartureBeforeArrivalException.class)
    public ResponseEntity<ErrorResponse> handleDepartureBeforeArrivalException(DepartureBeforeArrivalException ex, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
//    @ExceptionHandler(InvalidDateFormatException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidDateFormat(InvalidDateFormatException ex) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                new Date(),
//                ex.getMessage(),
//                "The date format provided is invalid. Please use the format: yyyy-MM-dd"
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//    }
//
//    @ExceptionHandler(InvalidTimeFormatException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidTimeFormat(InvalidTimeFormatException ex) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                new Date(),
//                ex.getMessage(),
//                "The time format provided is invalid. Please use the format: HH:mm"
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//    }
    // it will handle the generic exceptions

//     It will handle invalid date and time format exceptions
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
//        Throwable cause = ex.getCause();
//        if (cause instanceof DateTimeParseException) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid date or time format. Please use yyyy-MM-dd for dates and HH:mm:ss for times.");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("Invalid input format.");
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAnyException(
            Exception ex,
            WebRequest webRequest
    ) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /* it will handle the exception
       for the validation errors for both user and product entity
    */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error)->{
                    String field = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(field,message);
                }
        );
        return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }

}