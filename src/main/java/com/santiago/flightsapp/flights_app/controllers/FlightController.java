package com.santiago.flightsapp.flights_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiago.flightsapp.flights_app.dto.BookFlightDto;
import com.santiago.flightsapp.flights_app.dto.FlightDto;
import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.exceptions.FlightNotFoundException;
import com.santiago.flightsapp.flights_app.services.FlightService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService service;

    @GetMapping
    public List<FlightDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {

        Optional<FlightDto> flightOptional = service.findById(id);

        return ResponseEntity.ok(flightOptional.orElseThrow(() -> new FlightNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Flight flight) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(flight));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Flight flight) {
        return ResponseEntity.ok(service.update(id, flight).orElseThrow(() -> new FlightNotFoundException(id)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return ResponseEntity.ok(service.delete(id).orElseThrow(()-> new FlightNotFoundException(id)));
    }

    @PostMapping("/bookFlight")
    public ResponseEntity<?> bookFlight(@RequestBody BookFlightDto body) {
        return ResponseEntity.ok(service.bookFlight(body.flightId(), body.userId()).orElseThrow(()-> new FlightNotFoundException(body.flightId())));
    }
    

}
