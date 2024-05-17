package com.team1.technikon.controller;

import com.team1.technikon.dto.ApplicationErrorResponse;
import com.team1.technikon.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOwnerNotFoundException(OwnerNotFoundException e) {
        ErrorResponse errorResponse = new ApplicationErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(OwnerFailToCreateException.class)
    public ResponseEntity<ErrorResponse> handleFailToCreateException(OwnerFailToCreateException e) {
        ErrorResponse errorResponse = new ApplicationErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(RepairNotFoundException.class)
    public ResponseEntity<ApplicationErrorResponse> handleRepairNotFoundException(RepairNotFoundException e) {
        ApplicationErrorResponse errorResponse = new ApplicationErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RepairFailToCreateException.class)
    public ResponseEntity<ApplicationErrorResponse> handleRepairFailToCreateException(RepairFailToCreateException e) {
        ApplicationErrorResponse errorResponse = new ApplicationErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApplicationErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        ApplicationErrorResponse errorResponse = new ApplicationErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EntityFailToCreateException.class)
    public ResponseEntity<ApplicationErrorResponse> handleEntityFailToCreateException(EntityFailToCreateException e) {
        ApplicationErrorResponse errorResponse = new ApplicationErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApplicationErrorResponse> handleEntityFailToCreateException(InvalidInputException e) {
        ApplicationErrorResponse errorResponse = new ApplicationErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
