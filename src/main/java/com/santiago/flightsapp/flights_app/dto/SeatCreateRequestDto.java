package com.santiago.flightsapp.flights_app.dto;

import com.santiago.flightsapp.flights_app.entities.ClassType;
import com.santiago.flightsapp.flights_app.entities.Status;

import jakarta.validation.constraints.NotNull;

public class SeatCreateRequestDto {
    private Long id;
    private String number;
    private Status status = Status.AVAILABLE;
    private ClassType classType = ClassType.ECONOMY;

    @NotNull(message = "el id del vuelo (flightId) es requerido")
    private String flightId;

    public SeatCreateRequestDto() {
    }

    public SeatCreateRequestDto(Long id, String number, Status status, ClassType classType, String flightId) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.classType = classType;
        this.flightId = flightId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

}
