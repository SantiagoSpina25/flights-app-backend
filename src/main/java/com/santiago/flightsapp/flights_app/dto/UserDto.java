package com.santiago.flightsapp.flights_app.dto;

//import java.util.List;

import com.santiago.flightsapp.flights_app.entities.User;

public record UserDto(
    Long id,
    String username,
    String password
    //,List<FlightDto> seats
) {

    public static UserDto toDto(User user) {

        // List<FlightDto> flightDtos = user.getFlights().stream().map(f -> FlightDto.toDto(f)).toList();

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword()
                //,flightDtos
                );
    }
} 
