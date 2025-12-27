package com.example.airline.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    // Has an identity → Entity
    //May change over time
    //Used by Flight

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int totalEconomySeats;

    @Column(nullable = false)
    private int totalBusinessSeats;

    // for jpa
    protected Aircraft(){}

    public Aircraft(String model, int totalEconomySeats, int totalBusinessSeats) {
        this.model = model;
        this.totalEconomySeats = totalEconomySeats;
        this.totalBusinessSeats = totalBusinessSeats;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getTotalEconomySeats() {
        return totalEconomySeats;
    }

    public int getTotalBusinessSeats() {
        return totalBusinessSeats;
    }

    // No setter (encapsulation)
    //The entity state should not be changed externally without control
    //Changes will be made through the service
}
