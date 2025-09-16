package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.repositories.AirlineRepository;

@Service
public class AirlineServiceImpl implements AirlineService{

    @Autowired
    private AirlineRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Airline> findAll() {
        return (List<Airline>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Airline> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Airline save(Airline airline) {
        return repository.save(airline);
    }

    @Transactional
    @Override
    public Optional<Airline> delete(Long id) {

        Optional<Airline> airlineOptional = repository.findById(id);

        if(airlineOptional.isPresent()){
            repository.deleteById(id);
        }

        return airlineOptional;
    }
    
}
