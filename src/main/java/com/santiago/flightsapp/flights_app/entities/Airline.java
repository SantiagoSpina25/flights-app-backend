package com.santiago.flightsapp.flights_app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Table(name = "airlines")
@Entity
@Getter
@Setter
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String name;

    @Size(max = 200)
    @Column(length = 200)
    private String description;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights = new ArrayList<>();

}
