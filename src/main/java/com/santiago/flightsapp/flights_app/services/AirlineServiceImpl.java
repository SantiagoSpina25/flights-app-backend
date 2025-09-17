package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiago.flightsapp.flights_app.dto.AirlineDto;
import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.repositories.AirlineRepository;

@Service
public class AirlineServiceImpl implements AirlineService{

    @Autowired
    private AirlineRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<AirlineDto> findAll() {
        List<Airline> airlinesList = (List<Airline>) repository.findAll();
        return airlinesList.stream().map(a -> AirlineDto.toDto(a)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AirlineDto> findById(Long id) {
        return repository.findById(id).map(a -> AirlineDto.toDto(a));
    }

    @Transactional
    @Override
    public AirlineDto save(Airline airline) {
        return AirlineDto.toDto(repository.save(airline));
    }

    @Transactional
    @Override
    public Optional<AirlineDto> delete(Long id) {

        Optional<AirlineDto> airlineOptional = repository.findById(id).map(a-> AirlineDto.toDto(a));

        if(airlineOptional.isPresent()){
            repository.deleteById(id);
        }

        return airlineOptional;
    }
    
}
