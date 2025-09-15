package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiago.flightsapp.flights_app.entities.User;
import com.santiago.flightsapp.flights_app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> delete(Long id) {

        Optional<User> userOptional = repository.findById(id);

        if(userOptional.isPresent()){
            repository.deleteById(id);
        }
        
        return userOptional;
    }

    
    
}
