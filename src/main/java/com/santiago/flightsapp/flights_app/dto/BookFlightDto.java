package com.santiago.flightsapp.flights_app.dto;

//DTO PARA RESERVAR UN VUELO
public record BookFlightDto (
    String flightId,
    Long userId
){}
