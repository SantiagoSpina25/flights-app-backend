package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.repositories.FligthRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FligthRepository repository;

    @Override
    public List<Flight> findAll() {
        return (List<Flight>) repository.findAll();
    }

    @Override
    public Optional<Flight> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flight save(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public Optional<Flight> update(String id, Flight flight) {
        Optional<Flight> flightOptional = repository.findById(id);
        
        if(flightOptional.isPresent()){
            Flight newFlight = new Flight();
            newFlight.setOrigin(flight.getOrigin());
            newFlight.setDestination(flight.getDestination());
            newFlight.setDate(flight.getDate());
            newFlight.setHour(flight.getHour());
            newFlight.setStatus(flight.getStatus());

            return Optional.of(repository.save(newFlight));
        }

        return flightOptional;
    }

    @Override
    public Optional<Flight> delete(String id) {
        Optional<Flight> flightOptional = repository.findById(id);
        
        if(flightOptional.isPresent()){
            repository.deleteById(id);
        }

        return flightOptional;
    }

}
