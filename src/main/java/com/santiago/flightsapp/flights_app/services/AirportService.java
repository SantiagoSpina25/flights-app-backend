package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.dto.AirportDto;
import com.santiago.flightsapp.flights_app.entities.Airport;

public interface AirportService {
    List<AirportDto> findAll();

    Optional<AirportDto> findById(Long id);

    AirportDto save(Airport airport);

    Optional<AirportDto> delete(Long id);
}
