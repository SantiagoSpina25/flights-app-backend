package com.santiago.flightsapp.flights_app.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(Long id) {
        super("El aeropuerto con el id " + id + " no fue encontrado");
    }
}