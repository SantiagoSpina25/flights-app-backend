package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.entities.Flight;

public interface FlightService {
    List<Flight> findAll();

    Optional<Flight> findById(String id);

    Flight save(Flight flight);

    Optional<Flight> update(String id, Flight flight);

    Optional<Flight> delete(String id);
}
