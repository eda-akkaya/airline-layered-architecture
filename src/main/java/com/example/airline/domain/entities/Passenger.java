package com.example.airline.domain.entities;

import com.example.airline.domain.enums.PassengerType;
import jakarta.persistence.*;

@Entity
@Table(name="passengers")
public class Passenger {
    // It has an identity → Entity
    //It can change over time

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PassengerType passengerType;

    protected Passenger(){}

    public Passenger(String fullName, PassengerType passengerType) {
        this.fullName = fullName;
        this.passengerType = passengerType;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }
}
