package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.dto.AirlineDto;
import com.santiago.flightsapp.flights_app.entities.Airline;

public interface AirlineService {
    List<AirlineDto> findAll();

    Optional<AirlineDto> findById(Long id);

    AirlineDto save(Airline airline);

    Optional<AirlineDto> delete(Long id);
}
