package com.santiago.flightsapp.flights_app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiago.flightsapp.flights_app.dto.UserDto;
import com.santiago.flightsapp.flights_app.entities.User;
import com.santiago.flightsapp.flights_app.exceptions.notFound.UserNotFoundException;
import com.santiago.flightsapp.flights_app.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<UserDto> userOptional = service.findById(id);
        
        return ResponseEntity.ok(userOptional.orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PostMapping("/register")
    public ResponseEntity<?> create(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id).orElseThrow(() -> new UserNotFoundException(id));
        Map<String, String> json = new HashMap<>();
        json.put("message", "Usuario con el id " + id + " borrado correctamente!");

        return ResponseEntity.ok().body(json);
    }

}
