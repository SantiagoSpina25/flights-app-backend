package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiago.flightsapp.flights_app.dto.SeatCreateRequestDto;
import com.santiago.flightsapp.flights_app.dto.SeatDto;
import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.entities.Seat;
import com.santiago.flightsapp.flights_app.entities.Status;
import com.santiago.flightsapp.flights_app.entities.User;
import com.santiago.flightsapp.flights_app.exceptions.SeatNotAvailableException;
import com.santiago.flightsapp.flights_app.exceptions.notFound.FlightNotFoundException;
import com.santiago.flightsapp.flights_app.exceptions.notFound.SeatNotFoundException;
import com.santiago.flightsapp.flights_app.exceptions.notFound.UserNotFoundException;
import com.santiago.flightsapp.flights_app.repositories.FlightRepository;
import com.santiago.flightsapp.flights_app.repositories.SeatRepository;
import com.santiago.flightsapp.flights_app.repositories.UserRepository;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository repository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public List<SeatDto> findAll() {
        List<Seat> seatList = (List<Seat>) repository.findAll();
        return seatList.stream().map(s -> SeatDto.toDto(s)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<SeatDto> findById(Long id) {
        return repository.findById(id).map(s -> SeatDto.toDto(s));
    }

    @Transactional
    @Override
    public SeatDto save(SeatCreateRequestDto seat) {

        // Comprobar que el flightId no es nulo
        Flight flight = flightRepository.findById(seat.getFlightId())
                .orElseThrow(() -> new FlightNotFoundException(seat.getFlightId()));

        Seat newSeat = new Seat();

        newSeat.setId(seat.getId());
        newSeat.setNumber(seat.getNumber());
        newSeat.setStatus(seat.getStatus());
        newSeat.setClassType(seat.getClassType());
        newSeat.setFlight(flight);

        return SeatDto.toDto(repository.save(newSeat));
    }
    
    @Transactional
    @Override
    public Optional<SeatDto> delete(Long id) {

        Optional<SeatDto> seatOptional = repository.findById(id).map(s -> SeatDto.toDto(s));

        if (seatOptional.isPresent()) {
            repository.deleteById(id);
        }

        return seatOptional;

    }

    @Transactional
    @Override
    public Optional<SeatDto> bookSeat(Long seatId, Long userId) {

        Optional<Seat> seatOptional = repository.findById(seatId);
        Optional<User> userOptional = userRepository.findById(userId);

        // Verifica si existe el asiento
        if (!seatOptional.isPresent()) {
            throw new SeatNotFoundException(seatId);
        }
        
        // verifica si existe el usuario
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        //Verifica si el asiento sigue disponible
        if(seatOptional.get().getStatus() != Status.AVAILABLE){
            throw new SeatNotAvailableException(seatId);
        }

        //Si todo esta bien, le agrega el usuario al asiento
        Seat seat = seatOptional.get();
        User user = userOptional.get();

        seat.setUser(user);
        seat.setStatus(Status.SOLD);

        //Obtengo la lista de asientos del usuario y le agrego el nuevo
        List<Seat> userSeats = user.getSeats();
        userSeats.add(seat);
        user.setSeats(userSeats);
        
        userRepository.save(user);

        Seat saved = repository.save(seat);

        return Optional.of(SeatDto.toDto(saved));
    }

}
