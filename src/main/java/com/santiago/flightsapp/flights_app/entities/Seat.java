package com.santiago.flightsapp.flights_app.entities;

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
    private double price= 1000;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne(optional = true) // Opcional porque un asiento puede no tener un usuario asociado
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    // Funcion para actualizar precio segun la clase
    public void updatePriceByClassType() {
        double basePrice = 1000;

        switch (this.classType) {
            case ECONOMY:
                this.price = basePrice;
                break;
            case BUSINESS:
                this.price = basePrice * 1.70;
                break;
            case FIRST_CLASS:
                this.price = basePrice * 2.20;
                break;
            default:
                this.price = basePrice;
        }
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
        updatePriceByClassType();
    }

}
