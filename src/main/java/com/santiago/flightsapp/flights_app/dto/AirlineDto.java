package com.santiago.flightsapp.flights_app.dto;

import java.util.List;

import com.santiago.flightsapp.flights_app.entities.Airline;

public record AirlineDto(
        Long id,
        String name,
        String description,
        List<FlightDto> flights) {
            
    public static AirlineDto toDto(Airline airline) {

        List<FlightDto> flightDtos = airline.getFlights().stream().map(f -> FlightDto.toDto(f)).toList();

        return new AirlineDto(
                airline.getId(),
                airline.getName(),
                airline.getDescription(),
                flightDtos);
    }
}