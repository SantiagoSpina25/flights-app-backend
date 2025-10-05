package com.santiago.flightsapp.flights_app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Table(name = "flights")
@Entity
@Data
public class Flight {

    @Id
    @Column(length = 6)
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String origin;

    @NotBlank
    @Column(nullable = false)
    private String destination;

    @NotNull
    private LocalDate date = LocalDate.now(); // Por defecto la fecha actual

    @NotNull
    private LocalTime hour = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // Por defecto la hora actual (chronoUnit corta los milisegundos)

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

}
