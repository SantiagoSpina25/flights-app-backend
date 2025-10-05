package com.santiago.flightsapp.flights_app.exceptions.notFound;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {
     public FlightNotFoundException(String id){
        super("El vuelo con el id " + id + " no fue encontrado");
    }
}