package com.santiago.flightsapp.flights_app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.santiago.flightsapp.flights_app.MockData;
import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.repositories.AirlineRepository;
import com.santiago.flightsapp.flights_app.repositories.FlightRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightsRepository;

    @Autowired
    private AirlineRepository airlinesRepository;

    @Test
    void save_withAirline_and_findById() {
        Airline airline = airlinesRepository.save(MockData.airline("Iberia"));
        Flight f = MockData.flight("IBE421", "Madrid", "Roma", airline, null);

        flightsRepository.save(f);

        Optional<Flight> found = flightsRepository.findById("IBE421");
        assertTrue(found.isPresent());
        assertEquals("Madrid", found.get().getOrigin());
        assertEquals(airline.getId(), found.get().getAirline().getId());
    }

    @Test
    void deleteById_removesFlight() {
        Airline iberia = airlinesRepository.save(MockData.airline("Iberia"));
        flightsRepository.save(MockData.flight("IBE999", "Bilbao", "Múnich", iberia, null));

        flightsRepository.deleteById("IBE999");

        assertFalse(flightsRepository.findById("IBE999").isPresent());
    }

}
