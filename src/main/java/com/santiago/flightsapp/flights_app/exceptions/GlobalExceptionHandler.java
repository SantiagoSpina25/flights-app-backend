package com.santiago.flightsapp.flights_app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Excepcion que ocurre cuando no se encuentra un usuario con el id pasado por
    // parametros
    @ExceptionHandler({UserNotFoundException.class, FlightNotFoundException.class, AirlineNotFoundException.class})
    public ResponseEntity<ErrorResponse> idNotfound(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(404, "Error id no encontrado", e.getMessage());

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

    //Excepcion que ocurre cuando hay un error en la validacion de una entidad
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MultipleErrorsResponse> validationError(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getFieldErrors().forEach(er->{
            errors.put(er.getField(), "Error en el campo '" + er.getField() + "', " + er.getDefaultMessage());
        });
        MultipleErrorsResponse errorResponse = new MultipleErrorsResponse(400, errors, "Los argumentos introducidos no son validos");

        return ResponseEntity.status(400).body(errorResponse);
    }

}
