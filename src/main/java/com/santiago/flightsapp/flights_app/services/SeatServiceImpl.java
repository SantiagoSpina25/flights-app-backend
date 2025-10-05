package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiago.flightsapp.flights_app.dto.SeatCreateRequestDto;
import com.santiago.flightsapp.flights_app.dto.SeatDto;
import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.entities.Seat;
import com.santiago.flightsapp.flights_app.exceptions.notFound.FlightNotFoundException;
import com.santiago.flightsapp.flights_app.repositories.FlightRepository;
import com.santiago.flightsapp.flights_app.repositories.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository repository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<SeatDto> findAll() {
        List<Seat> seatList = (List<Seat>) repository.findAll();
        return seatList.stream().map(s-> SeatDto.toDto(s)).toList();
    }

    @Override
    public Optional<SeatDto> findById(Long id) {
        return repository.findById(id).map(s-> SeatDto.toDto(s));
    }

    @Override
    public SeatDto save(SeatCreateRequestDto seat) {

        //Comprobar que el flightId no es nulo
        Flight flight = flightRepository.findById(seat.getFlightId()).orElseThrow(()-> new FlightNotFoundException(seat.getFlightId()));

        Seat newSeat = new Seat();

        newSeat.setId(seat.getId());
        newSeat.setNumber(seat.getNumber());
        newSeat.setStatus(seat.getStatus());
        newSeat.setClassType(seat.getClassType());
        newSeat.setFlight(flight);

        return SeatDto.toDto(repository.save(newSeat));
    }

    @Override
    public Optional<SeatDto> delete(Long id) {

        Optional<SeatDto> seatOptional = repository.findById(id).map(s-> SeatDto.toDto(s));

        if (seatOptional.isPresent()) {
            repository.deleteById(id);
        }

        return seatOptional;

    }

}
