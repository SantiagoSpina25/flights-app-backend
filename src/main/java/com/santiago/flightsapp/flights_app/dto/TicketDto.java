package com.santiago.flightsapp.flights_app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.santiago.flightsapp.flights_app.entities.ClassType;

public record TicketDto(
    String flightId,
    String airline,
    LocalDate date,
    LocalTime hour,
    String origin,
    String destination,
    ClassType classType,
    String seatNumber

) {
}
    

