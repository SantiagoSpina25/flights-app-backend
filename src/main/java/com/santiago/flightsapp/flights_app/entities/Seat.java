package com.santiago.flightsapp.flights_app.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "seats")
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassType classType = ClassType.ECONOMY; // Por defecto Economy

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE; // Por defecto disponible

    @Column(nullable = false)
    private double price = 1000;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne(optional = true) // Opcional porque un asiento puede no tener un usuario asociado
    @JoinColumn(name = "user_id", nullable = true)
    private User user;


    //Metodo que actualiza el precio segun la distancia del vuelo y la clase del asiento
    public void updatePriceByDistance(BigDecimal distanceKm) {
        double basePrice = 1000.0;

        switch (classType) {
            case ECONOMY:
                price = basePrice;
                break;
            case BUSINESS:
                price = basePrice * 1.70;
                break;
            case FIRST_CLASS:
                price = basePrice * 2.20;
                break;
            default:
                price = basePrice;
        }

        if (distanceKm != null) {
            double distance = distanceKm.doubleValue();
            double distanceMultiplier = 1.0;

            if (distance > 7000)
                distanceMultiplier = 1.60;
            else if (distance > 3000)
                distanceMultiplier = 1.40;
            else if (distance > 1500)
                distanceMultiplier = 1.25;
            else if (distance > 500)
                distanceMultiplier = 1.10;

            price = price * distanceMultiplier;
        }

        price = Math.round(price * 100.0) / 100.0;
    }

}
