package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.dto.FlightDto;
import com.santiago.flightsapp.flights_app.entities.Flight;

public interface FlightService {
    List<FlightDto> findAll();

    Optional<FlightDto> findById(String id);

    FlightDto save(Flight flight);

    Optional<FlightDto> update(String id, Flight flight);

    Optional<FlightDto> delete(String id);
}
