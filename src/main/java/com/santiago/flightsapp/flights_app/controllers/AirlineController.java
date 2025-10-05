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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiago.flightsapp.flights_app.dto.AirlineDto;
import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.exceptions.notFound.AirlineNotFoundException;
import com.santiago.flightsapp.flights_app.services.AirlineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    @Autowired
    private AirlineService service;

    @GetMapping
    public List<AirlineDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<AirlineDto> airlineOptional = service.findById(id);

        return ResponseEntity.ok(airlineOptional.orElseThrow(() -> new AirlineNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Airline airline) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(airline));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id).orElseThrow(() -> new AirlineNotFoundException(id));
        Map<String, String> json = new HashMap<>();
        json.put("message", "Aerolinea con el id " + id + " borrada correctamente!");

        return ResponseEntity.ok().body(json);
    }
}
