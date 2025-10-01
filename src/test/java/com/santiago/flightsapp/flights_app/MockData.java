package com.santiago.flightsapp.flights_app;


import com.santiago.flightsapp.flights_app.entities.Airline;
import com.santiago.flightsapp.flights_app.entities.Flight;
import com.santiago.flightsapp.flights_app.entities.User;

//Clase que crea rapidamente un usuario, una aerolinea y un vuelo 
public class MockData {
    public static User user(String username) {
        User u = new User();
        u.setUsername(username);
        u.setPassword("secret");
        return u;
    }

    public static Airline airline(String name) {
        Airline a = new Airline();
        a.setName(name);
        a.setDescription("description example");
        return a;
    }

    public static Flight flight(String id, String origin, String destination, Airline airline, User user) {
        Flight f = new Flight();
        f.setId(id);
        f.setOrigin(origin);
        f.setDestination(destination);
        f.setAirline(airline);
        f.setUser(user);
        return f;
    }
}
