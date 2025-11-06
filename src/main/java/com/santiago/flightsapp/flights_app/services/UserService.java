package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.dto.UserDto;
import com.santiago.flightsapp.flights_app.entities.User;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(User user);

    Optional<UserDto> addBalance(Long id, int balance);

    Optional<UserDto> delete(Long id);
} 