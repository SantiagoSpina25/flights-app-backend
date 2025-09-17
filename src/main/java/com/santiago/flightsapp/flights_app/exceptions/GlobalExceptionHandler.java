package com.santiago.flightsapp.flights_app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.santiago.flightsapp.flights_app.exceptions.user.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Excepcion que ocurre cuando no se encuentra un usuario con el id pasado por
    // parametros
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(404, "Error usuario no encontrado", e.getMessage());

        return ResponseEntity.status(404).body(errorResponse);
    }

    //Excepcion que ocurre cuando se pasa un parametro con un tipo erroneo
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> argumentMismatch(MethodArgumentTypeMismatchException e){
        String message = String.format("El parametro '%s' con el valor '%s' no es valido. Se esperaba un tipo '%s'", 
        e.getName(),
        e.getValue(),
        e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "desconocido");

        ErrorResponse errorResponse = new ErrorResponse(400, "Bad request", message);

        return ResponseEntity.status(400).body(errorResponse);
        
    }
}
