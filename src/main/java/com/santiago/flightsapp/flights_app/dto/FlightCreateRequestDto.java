package com.santiago.flightsapp.flights_app.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.santiago.flightsapp.flights_app.entities.Status;

import jakarta.validation.constraints.NotNull;

public class FlightCreateRequestDto {

    private String id;
    private String origin;
    private String destination;

    private LocalDate date = LocalDate.now();
    private LocalTime hour = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
    private Status status = Status.AVAILABLE;

    @NotNull(message = "el id de la aerolinea (airlineId) es requerido")
    private Long airlineId;

    public FlightCreateRequestDto() {}

    public FlightCreateRequestDto(String id, String origin, String destination, Long airlineId) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.airlineId = airlineId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        }
    }

    public LocalTime getHour() {
        return hour;
    }
    public void setHour(LocalTime hour) {
        if (hour != null) {
            this.hour = hour;
        }
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        if (status != null) {
            this.status = status;
        }
    }

    public Long getAirlineId() {
        return airlineId;
    }
    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }
}

