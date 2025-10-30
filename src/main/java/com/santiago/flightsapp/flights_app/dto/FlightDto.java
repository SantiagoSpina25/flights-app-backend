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
        String originCity,
        String destinationCity,
        String airlineName,
        SeatsInfo seats) {

    // Metodo que convierte Flight en FlightDto -> Le agrega el nombre de la
    // aerolinea en vez de mostrar
    // toda el objeto
    public static FlightDto toDto(Flight f) {

        int totalSeats = f.getSeats().size();
        int availableSeats = f.getSeats().stream().filter(seat -> seat.getStatus() == Status.AVAILABLE).toList().size();
        int soldSeats = f.getSeats().stream().filter(seat -> seat.getStatus() == Status.SOLD).toList().size();

        SeatsInfo seatsInfo = new SeatsInfo(totalSeats, availableSeats, soldSeats);

        String originCityAndAirport = f.getOrigin().getCity() + " (" + f.getOrigin().getIATACode() +")";
        String destinationCityAndAirport = f.getDestination().getCity() + " (" + f.getDestination().getIATACode() +")";

        return new FlightDto(
                f.getId(),
                f.getDate(),
                f.getHour(),
                originCityAndAirport,
                destinationCityAndAirport,
                f.getAirline() != null ? f.getAirline().getName() : null,
                seatsInfo);
    }
}

// Guarda la informacion de los asientos
record SeatsInfo(
        int total,
        int available,
        int sold) {
}