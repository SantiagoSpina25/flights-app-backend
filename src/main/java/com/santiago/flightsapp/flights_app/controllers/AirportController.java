package com.santiago.flightsapp.flights_app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiago.flightsapp.flights_app.dto.AirportDto;
import com.santiago.flightsapp.flights_app.entities.Airport;
import com.santiago.flightsapp.flights_app.exceptions.notFound.AirportNotFoundException;
import com.santiago.flightsapp.flights_app.services.AirportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin("http://localhost:5173")

public class AirportController {

    @Autowired
    private AirportService service;

    @GetMapping
    public List<AirportDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<AirportDto> airportOptional = service.findById(id);

        return ResponseEntity.ok(airportOptional.orElseThrow(() -> new AirportNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Airport airport) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(airport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id).orElseThrow(() -> new AirportNotFoundException(id));
        Map<String, String> json = new HashMap<>();
        json.put("message", "Aeropuerto con el id " + id + " borrada correctamente!");

        return ResponseEntity.ok().body(json);
    }
}
