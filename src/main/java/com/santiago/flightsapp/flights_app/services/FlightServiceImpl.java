package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiago.flightsapp.flights_app.dto.FlightCreateRequestDto;
import com.santiago.flightsapp.flights_app.dto.FlightDto;
import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.exceptions.notFound.AirlineNotFoundException;
import com.santiago.flightsapp.flights_app.repositories.AirlineRepository;
import com.santiago.flightsapp.flights_app.repositories.FlightRepository;


@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository repository;

    @Autowired
    private AirlineRepository airlineRepository;


    @Transactional(readOnly = true)
    @Override
    public List<FlightDto> findAll() {
        List<Flight> flightlList = (List<Flight>) repository.findAll();
        return flightlList.stream().map(f -> FlightDto.toDto(f)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<FlightDto> findById(String id) {
        return repository.findById(id).map(f -> FlightDto.toDto(f));
    }

    @Transactional
    @Override
    public FlightDto save(FlightCreateRequestDto flight) {

        //Compruebo primero que el id de la aerolinea introducida exista
        Airline airline = airlineRepository.findById(flight.getAirlineId()).orElseThrow(()-> new AirlineNotFoundException(flight.getAirlineId()));

        Flight newFlight = new Flight();
        newFlight.setId(flight.getId());
        newFlight.setOrigin(flight.getOrigin());
        newFlight.setDestination(flight.getDestination());
        newFlight.setDate(flight.getDate());
        newFlight.setHour(flight.getHour());
        newFlight.setAirline(airline);

        return FlightDto.toDto(repository.save(newFlight));
    }

    @Transactional
    @Override
    public Optional<FlightDto> update(String id, Flight flight) {
        return repository.findById(id).map(existingFlight -> {
            existingFlight.setOrigin(flight.getOrigin());
            existingFlight.setDestination(flight.getDestination());
            existingFlight.setDate(flight.getDate());
            existingFlight.setHour(flight.getHour());
            existingFlight.setAirline(flight.getAirline());

            Flight saved = repository.save(existingFlight);
            return FlightDto.toDto(saved);
        });
    }

    @Transactional
    @Override
    public Optional<FlightDto> delete(String id) {
        Optional<FlightDto> flightOptional = repository.findById(id).map(f-> FlightDto.toDto(f));

        if (flightOptional.isPresent()) {
            repository.deleteById(id);
        }

        return flightOptional;
    }

    //METODO PARA RESERVAR UN VUELO
    // @Override
    // public Optional<FlightDto> bookFlight(String flightId, Long userId) {
    //     return null;
    //     // return repository.findById(flightId).map(f -> {

    //     //     //verifica si existe el usuario
    //     //     if(em.find(User.class, userId) == null){
    //     //         throw new UserNotFoundException(userId);
    //     //     }

    //     //     //Verifica si el vuelo sigue disponible
    //     //     if (f.getStatus() != Status.AVAILABLE) {
    //     //         throw new FlightNotAvailableException(flightId);
    //     //     }

    //     //     //Se le asigna el usuario al vuelo
    //     //     //Uso entity manager porque es mas practico para asignar una fk (no trae toda la entidad)
    //     //     f.setUser(em.getReference(User.class, userId));
    //     //     f.setStatus(Status.SOLD); 

    //     //     //Guarda el vuelo
    //     //     Flight savedFlight = repository.save(f);

    //     //     return FlightDto.toDto(savedFlight);
    //     // });
    // }

}
