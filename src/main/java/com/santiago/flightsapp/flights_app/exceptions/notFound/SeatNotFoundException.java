package com.santiago.flightsapp.flights_app.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeatNotFoundException extends RuntimeException {
     public SeatNotFoundException(Long id){
        super("El asiento con el id " + id + " no fue encontrado");
    }
}