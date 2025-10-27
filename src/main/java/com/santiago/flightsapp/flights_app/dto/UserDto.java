package com.santiago.flightsapp.flights_app.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.entities.User;

public record UserDto(
        Long id,
        String username,
        String password,
        boolean admin,
        List<TicketDto> tickets) {

    // Metodo para convertir a DTO
    public static UserDto toDto(User user) {

        // Creo la lista de tickets del usuario
        // Obtiene informacion del asiento y del vuelo
        List<TicketDto> tickets = user.getSeats().stream().map(s -> {
            Flight flight = s.getFlight();
            return new TicketDto(
                    flight.getId(),
                    flight.getAirline().getName(), flight.getDate(), flight.getHour(), flight.getOrigin(),
                    flight.getDestination(), s.getClassType(), s.getNumber());
        }).toList();

        List<String> roleNames = user.getRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());

        boolean isAdmin = roleNames.stream().anyMatch(role -> "ROLE_ADMIN".equals(role));

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                isAdmin,
                tickets);
    }
}
