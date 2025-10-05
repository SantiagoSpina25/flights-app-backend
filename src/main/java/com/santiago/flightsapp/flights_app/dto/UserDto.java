package com.santiago.flightsapp.flights_app.dto;

import java.util.List;

import com.santiago.flightsapp.flights_app.entities.User;

public record UserDto(
        Long id,
        String username,
        String password,
        List<SeatDto> seats) {

    public static UserDto toDto(User user) {

        List<SeatDto> seats = user.getSeats().stream().map(s -> SeatDto.toDto(s)).toList();

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                seats);
    }
}
