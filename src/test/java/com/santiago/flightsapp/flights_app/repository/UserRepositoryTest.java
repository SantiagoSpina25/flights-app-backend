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
import com.santiago.flightsapp.flights_app.entities.User;
import com.santiago.flightsapp.flights_app.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    //Test para probar si guarda correctamente un usuario y si lo encuentra por id y por username
    //Tambien comprueba el caso cuando no lo encuentra por id
    @Test
    void saveAndFindById() {
        User u = MockData.user("santiago");
        User saved = repository.save(u);

        Optional<User> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("santiago", found.get().getUsername());
        //assertThrows(UserNotFoundException.class, repository.findById(-1L));
    }

    //Test que comprueba findByUsername
    @Test
    void findByUsername() {
        repository.save(MockData.user("usuario1"));

        assertTrue(repository.findByUsername("usuario1").isPresent());
        assertFalse(repository.findByUsername("usuarioInexistente").isPresent());
    }

}
