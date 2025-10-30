package com.santiago.flightsapp.flights_app.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.santiago.flightsapp.flights_app.entities.Status;

import jakarta.validation.constraints.NotNull;

public class FlightCreateRequestDto {

    private String id;

    @NotNull(message = "el id del aeropuerto de origen (originAirportId) es requerido")
    private Long originAirportId;
    @NotNull(message = "el id del aeropuerto de destino (destinationAirportId) es requerido")
    private Long destinationAirportId;

    private LocalDate date = LocalDate.now();
    private LocalTime hour = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
    private Status status = Status.AVAILABLE;

    @NotNull(message = "el id de la aerolinea (airlineId) es requerido")
    private Long airlineId;

    public FlightCreateRequestDto() {
    }

    public FlightCreateRequestDto(String id, Long originAirportId, Long destinationAirportId, Long airlineId) {
        this.id = id;
        this.originAirportId = originAirportId;
        this.destinationAirportId = destinationAirportId;
        this.airlineId = airlineId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOriginAirportId() {
        return originAirportId;
    }

    public void setOriginAirportId(Long originAirportId) {
        this.originAirportId = originAirportId;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(Long destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
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
