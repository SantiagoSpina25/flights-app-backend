package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiago.flightsapp.flights_app.dto.AirportDto;
import com.santiago.flightsapp.flights_app.entities.Airport;
import com.santiago.flightsapp.flights_app.repositories.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<AirportDto> findAll() {
        List<Airport> airportsList = (List<Airport>) repository.findAll();
        return airportsList.stream().map(a -> AirportDto.toDto(a)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AirportDto> findById(Long id) {
        return repository.findById(id).map(a -> AirportDto.toDto(a));
    }

    @Transactional
    @Override
    public AirportDto save(Airport airport) {
        return AirportDto.toDto(repository.save(airport));
    }

    @Transactional
    @Override
    public Optional<AirportDto> delete(Long id) {
        Optional<AirportDto> airportOptional = repository.findById(id).map(a-> AirportDto.toDto(a));
        if (airportOptional.isPresent()) {
            repository.deleteById(id);
        }

        return airportOptional;
    }

}
