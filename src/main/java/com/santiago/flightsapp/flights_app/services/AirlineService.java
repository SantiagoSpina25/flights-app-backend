package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.entities.User;

public interface AirlineService {
    List<Airline> findAll();

    Optional<Airline> findById(Long id);

    Airline save(Airline airline);

    Optional<Airline> delete(Long id);
}
