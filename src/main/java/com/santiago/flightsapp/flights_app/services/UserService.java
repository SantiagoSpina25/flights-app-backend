package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import com.santiago.flightsapp.flights_app.entities.User;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> delete(Long id);
} 