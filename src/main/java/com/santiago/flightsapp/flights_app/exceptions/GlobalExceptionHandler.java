package com.santiago.flightsapp.flights_app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Excepcion que ocurre cuando no se encuentra un usuario con el id pasado por
    // parametros
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(404, "Error user not found", e.getMessage());

        return ResponseEntity.status(404).body(errorResponse);
    }
}
