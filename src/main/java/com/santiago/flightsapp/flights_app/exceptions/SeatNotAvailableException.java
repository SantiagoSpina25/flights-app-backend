package com.santiago.flightsapp.flights_app.exceptions;

public class SeatNotAvailableException extends RuntimeException{
    public SeatNotAvailableException(Long id){
        super("El asiento con id '" + id + "' ya no esta disponible");
    }
}
