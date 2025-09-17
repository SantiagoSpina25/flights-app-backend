package com.santiago.flightsapp.flights_app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santiago.flightsapp.flights_app.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
