package com.santiago.flightsapp.flights_app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    @Column(nullable = false, length = 30)
    private String username;

    @NotBlank
    @Size(min = 4, max = 80)
    @Column(nullable = false, length = 80)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    private List<Flight> flights = new ArrayList<>();

}
