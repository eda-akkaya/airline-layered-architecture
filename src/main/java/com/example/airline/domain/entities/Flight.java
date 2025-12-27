package com.example.airline.domain.entities;

import com.example.airline.domain.valueObjects.Airport;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
// It is the center of the system
//It is related to aircraft
//It owns the reservations

    // A flight has only one aircraft
    //An aircraft can be used on more than one flight

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String flightNumber;

    // @Embedded is a way of telling JPA that a class:
    //Is not an entity
    //Has no identity
    //Is part of another entity
    //
    //"This object is not a separate table.
    //It should be embedded in the table of the owning entity."
    @Embedded
    private Airport departureAirport;
    // This line takes the fields from the Airport class
    //and converts them into columns in the Flight table
    //It does not create a separate airport table
    //
    //Airport class:
    //code
    //city
    //
    //Columns created in the Flight table:
    //departure_airport_code
    //departure_airport_city

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "arrival_code")),
            @AttributeOverride(name = "city", column = @Column(name = "arrival_city"))
    })
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Aircraft aircraft;

    protected Flight(){}

    public Flight(String flightNumber, Airport departureAirport, Airport arrivalAirport, LocalDateTime departureTime, Aircraft aircraft) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.aircraft = aircraft;
    }

    public long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

}
