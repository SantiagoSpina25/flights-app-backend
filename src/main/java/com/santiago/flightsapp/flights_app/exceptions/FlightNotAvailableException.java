package com.santiago.flightsapp.flights_app.exceptions;

public class FlightNotAvailableException extends RuntimeException{
    public FlightNotAvailableException(String id){
        super("El vuelo '" + id + "' ya no esta disponible");
    }
}
