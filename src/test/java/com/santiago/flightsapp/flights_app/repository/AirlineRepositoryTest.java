package com.santiago.flightsapp.flights_app.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.santiago.flightsapp.flights_app.MockData;
import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.repositories.AirlineRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AirlineRepositoryTest {

    @Autowired
    private AirlineRepository repository;

    //Comprueba si consigue guardar una nueva aerolinea y si la encuentra por id
    @Test
    void saveAndFindById() {
        Airline airline = MockData.airline("Ryanair");
        Airline saved = repository.save(airline);

        assertTrue(repository.findById(saved.getId()).isPresent());
    }

    //Comprueba que borra correctamente una aerolinea
    @Test
    void deleteById() {
        Airline airline = repository.save(MockData.airline("Vueling"));
        Long id = airline.getId();

        repository.deleteById(id);

        assertFalse(repository.findById(id).isPresent());
    }
}
