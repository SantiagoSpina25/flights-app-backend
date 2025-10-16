package com.santiago.flightsapp.flights_app.dto;

public record SeatsForFlightCreateDto(
        String flightId,
        int numberOfSeats) {
}
