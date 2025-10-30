package com.santiago.flightsapp.flights_app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Table(name = "flights")
@Entity
@Data
public class Flight {

    @Id
    @Column(length = 6)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "originAirport_Id", nullable = false)
    private Airport origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinationAirport_Id", nullable = false)
    private Airport destination;

    @NotNull
    private LocalDate date = LocalDate.now(); // Por defecto la fecha actual

    @NotNull
    private LocalTime hour = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // Por defecto la hora actual (chronoUnit corta los milisegundos)

    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @OneToMany(mappedBy = "flight")
    private List<Seat> seats = new ArrayList<>();

}
