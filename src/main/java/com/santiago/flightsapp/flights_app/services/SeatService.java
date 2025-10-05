package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.dto.SeatCreateRequestDto;
import com.santiago.flightsapp.flights_app.dto.SeatDto;

public interface SeatService{
    List<SeatDto> findAll();

    Optional<SeatDto> findById(Long id);

    SeatDto save(SeatCreateRequestDto seat);

    Optional<SeatDto> delete(Long id);

    Optional<SeatDto> bookSeat(Long seatId, Long userId);
}
