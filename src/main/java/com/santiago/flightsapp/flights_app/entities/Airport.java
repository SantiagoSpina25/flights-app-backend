package com.santiago.flightsapp.flights_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "airports")
@Entity
@Data
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iata_code", nullable = false, length = 3)
    private String IATACode;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 80)
    private String city;

    @Column(nullable = false, length = 10)
    private double latitude;

    @Column(nullable = false, length = 10)
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
