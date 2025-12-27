package com.example.airline.domain.entities;
// Reservation:
//Represents a transaction
//Combines Flight + Passenger + SeatType
//Is the center of business rules
//Therefore -> Reservation = Aggregate Root
//
//Aggregate Root:
//Reservation maintains its own rules
//The outside world cannot break down the reservation
//Everything happens through the Reservation

import com.example.airline.domain.enums.ReservationStatus;
import com.example.airline.domain.valueObjects.Money;
import com.example.airline.domain.valueObjects.SeatType;
import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Flight flight;
    // optional = false meaning: This relationship is mandatory under the domain rule.
    //A Reservation must be linked to a Flight.
    //A Reservation cannot exist without a Flight.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Embedded
    private Money price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus reservationStatus;

    public Reservation(Passenger passenger, Flight flight, SeatType seatType, Money price) {
        this.passenger = passenger;
        this.flight = flight;
        this.seatType = seatType;
        this.price = price;
    }

    public Long getId(){
        return id;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public Flight getFlight() {
        return flight;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public Money getPrice() {
        return price;
    }
}
