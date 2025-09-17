package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiago.flightsapp.flights_app.dto.FlightDto;
import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.repositories.FligthRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FligthRepository repository;

    @Override
    public List<FlightDto> findAll() {
        List<Flight> flightlList = (List<Flight>) repository.findAll();
        return flightlList.stream().map(f -> FlightDto.toDto(f)).toList();
    }

    @Override
    public Optional<FlightDto> findById(String id) {
        return repository.findById(id).map(f -> FlightDto.toDto(f));
    }

    @Override
    public FlightDto save(Flight flight) {
        return FlightDto.toDto(repository.save(flight));
    }

    @Override
    public Optional<FlightDto> update(String id, Flight flight) {
        return repository.findById(id).map(existingFlight -> {
            existingFlight.setOrigin(flight.getOrigin());
            existingFlight.setDestination(flight.getDestination());
            existingFlight.setDate(flight.getDate());
            existingFlight.setHour(flight.getHour());
            existingFlight.setStatus(flight.getStatus());
            existingFlight.setAirline(flight.getAirline());
            existingFlight.setUser(flight.getUser());

            Flight saved = repository.save(existingFlight);
            return FlightDto.toDto(saved);
        });
    }

    @Override
    public Optional<FlightDto> delete(String id) {
        Optional<FlightDto> flightOptional = repository.findById(id).map(f-> FlightDto.toDto(f));

        if (flightOptional.isPresent()) {
            repository.deleteById(id);
        }

        return flightOptional;
    }

}
