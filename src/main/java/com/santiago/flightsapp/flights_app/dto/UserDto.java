package com.santiago.flightsapp.flights_app.dto;

import java.util.List;

import com.santiago.flightsapp.flights_app.entities.Seat;
import com.santiago.flightsapp.flights_app.entities.User;

public record UserDto(
        Long id,
        String username,
        String password, List<Seat> seats) {

    public static UserDto toDto(User user) {

        List<Seat> seats = user.getSeats();

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                seats);
    }
}
