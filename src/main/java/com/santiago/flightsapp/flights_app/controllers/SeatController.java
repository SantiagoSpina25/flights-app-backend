package com.santiago.flightsapp.flights_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiago.flightsapp.flights_app.dto.SeatCreateRequestDto;
import com.santiago.flightsapp.flights_app.dto.SeatDto;
import com.santiago.flightsapp.flights_app.exceptions.notFound.SeatNotFoundException;
import com.santiago.flightsapp.flights_app.services.SeatService;

import jakarta.validation.Valid;

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

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService service;

    @GetMapping
    public List<SeatDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<SeatDto> seatOptional = service.findById(id);
        return ResponseEntity.ok(seatOptional.orElseThrow(() -> new SeatNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SeatCreateRequestDto seat) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(seat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id).orElseThrow(() -> new SeatNotFoundException(id));
        Map<String, String> json = new HashMap<>();
        json.put("message", "Asiento con el id " + id + " borrada correctamente!");

        return ResponseEntity.ok().body(json);
    }
}
