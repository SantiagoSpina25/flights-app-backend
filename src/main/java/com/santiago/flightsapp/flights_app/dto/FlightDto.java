package com.santiago.flightsapp.flights_app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.entities.Status;

//Record para Dto de flight (inmutable, genera constructor y getters automaticamente)
public record FlightDto(
        String id,
        LocalDate date,
        LocalTime hour,
        String origin,
        String destination,
        Status status,
        String airlineName

) {

    // Metodo que convierte Flight en FlightDto -> Le agrega el nombre de la
    // aerolinea en vez de mostrar
    // toda el objeto
    public static FlightDto toDto(Flight f) {
        return new FlightDto(
                f.getId(),
                f.getDate(),
                f.getHour(),
                f.getOrigin(),
                f.getDestination(),
                f.getStatus(),
                f.getAirline() != null ? f.getAirline().getName() : null);
    }
}
