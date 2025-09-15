package com.santiago.flightsapp.flights_app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santiago.flightsapp.flights_app.entities.Airline;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, Long> {

}
