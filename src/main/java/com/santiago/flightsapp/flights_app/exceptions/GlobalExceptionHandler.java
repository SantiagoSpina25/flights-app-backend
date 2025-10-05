package com.santiago.flightsapp.flights_app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.santiago.flightsapp.flights_app.exceptions.notFound.AirlineNotFoundException;
import com.santiago.flightsapp.flights_app.exceptions.notFound.FlightNotFoundException;
import com.santiago.flightsapp.flights_app.exceptions.notFound.SeatNotFoundException;
import com.santiago.flightsapp.flights_app.exceptions.notFound.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Excepcion que ocurre cuando no se encuentra un usuario con el id pasado por
    // parametros
    @ExceptionHandler({ UserNotFoundException.class, FlightNotFoundException.class, AirlineNotFoundException.class, SeatNotFoundException.class })
    public ResponseEntity<ErrorResponse> idNotfound(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(404, "Error id no encontrado", e.getMessage());

        return ResponseEntity.status(404).body(errorResponse);
    }

    // Excepcion que ocurre cuando se pasa un parametro con un tipo erroneo
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> argumentMismatch(MethodArgumentTypeMismatchException e) {
        String message = String.format("El parametro '%s' con el valor '%s' no es valido. Se esperaba un tipo '%s'",
                e.getName(),
                e.getValue(),
                e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "desconocido");

        ErrorResponse errorResponse = new ErrorResponse(400, "Bad request", message);

        return ResponseEntity.status(400).body(errorResponse);

    }

    // Excepcion que ocurre cuando hay un error en la validacion de una entidad
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MultipleErrorsResponse> validationError(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getFieldErrors().forEach(er -> {
            errors.put(er.getField(), "Error en el campo '" + er.getField() + "', " + er.getDefaultMessage());
        });
        MultipleErrorsResponse errorResponse = new MultipleErrorsResponse(400, errors,
                "Los argumentos introducidos no son validos");

        return ResponseEntity.status(400).body(errorResponse);
    }

    // Excepcion que ocurre cuando se pone un endpoint erroneo
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFound(NoResourceFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(404, "Error recurso no encontrado", e.getMessage());

        return ResponseEntity.status(404).body(errorResponse);
    }

    // Excepcion que ocurre cuando se indica un metodo (por ejemplo POST en vez de
    // GET) que no es valido para el endpoint
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> methodNotSupported(HttpRequestMethodNotSupportedException e) {
        String message = String.format("El metodo '%s' no esta permitido en este endpoint. Metodos soportados: %s",
                e.getMethod(), e.getSupportedHttpMethods() != null ? e.getSupportedHttpMethods() : "ninguno");

        ErrorResponse errorResponse = new ErrorResponse(405, "Error metodo erroneo", message);

        return ResponseEntity.status(405).body(errorResponse);
    }

    // Excepcion que ocurre cuando el body esta mal formado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> notReadable(HttpMessageNotReadableException e) {
        ErrorResponse errorResponse = new ErrorResponse(400, "Bad request", "El body de la peticion esta mal formado");

        return ResponseEntity.status(400).body(errorResponse);
    }

    // Excepcion que ocurre cuando el vuelo ya esta vendido y se le quiere asignar a otro usuario
    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<ErrorResponse> flightNotAvailable(SeatNotAvailableException e) {
        ErrorResponse errorResponse = new ErrorResponse(400, "Bad request", e.getMessage());

        return ResponseEntity.status(400).body(errorResponse);
    }

}
