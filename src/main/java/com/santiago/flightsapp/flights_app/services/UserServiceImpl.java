package com.santiago.flightsapp.flights_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiago.flightsapp.flights_app.dto.UserDto;
import com.santiago.flightsapp.flights_app.entities.User;
import com.santiago.flightsapp.flights_app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        List<User> users = (List<User>) repository.findAll();
        return users.stream().map(u->UserDto.toDto(u)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDto> findById(Long id) {
        return repository.findById(id).map(u->UserDto.toDto(u));
    }

    @Transactional
    @Override
    public UserDto save(User user) {
        //Codifica la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserDto.toDto(repository.save(user));
    }

    @Transactional
    @Override
    public Optional<UserDto> delete(Long id) {

        Optional<UserDto> userOptional = repository.findById(id).map(u->UserDto.toDto(u));

        if(userOptional.isPresent()){
            repository.deleteById(id);
        }
        
        return userOptional;
    }

    
    
}
