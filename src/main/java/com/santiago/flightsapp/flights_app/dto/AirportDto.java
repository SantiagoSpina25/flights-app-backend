package com.santiago.flightsapp.flights_app.dto;

import com.santiago.flightsapp.flights_app.entities.Airport;

public record AirportDto(
        Long id,
        String name,
        double latitude,
        double longitude,
        String countryName) {

    public static AirportDto toDto(Airport a) {
        return new AirportDto(
                a.getId(),
                a.getName(),
                a.getLatitude(),
                a.getLongitude(),
                a.getCountry().getName());
    }
}
