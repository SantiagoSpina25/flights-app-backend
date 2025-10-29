package com.santiago.flightsapp.flights_app.dto;

import com.santiago.flightsapp.flights_app.entities.ClassType;
import com.santiago.flightsapp.flights_app.entities.Seat;
import com.santiago.flightsapp.flights_app.entities.Status;

public record SeatDto(
    Long id,
    String number,
    Status status,
    ClassType classType,
    double price,
    String flightId
) {
    // Metodo que convierte Seat en SeatDto -> Le agrega el id del
    // vuelo en vez de mostrar todo el objeto

    public static SeatDto toDto(Seat s) {
        return new SeatDto(
                s.getId(),
                s.getNumber(),
                s.getStatus(),
                s.getClassType(),
                s.getPrice(),
                s.getFlight() != null ? s.getFlight().getId() : null);
    }
}
