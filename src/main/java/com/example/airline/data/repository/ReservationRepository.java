package com.example.airline.data.repository;

import com.example.airline.domain.entities.Flight;
import com.example.airline.domain.entities.Reservation;
import com.example.airline.domain.enums.ReservationStatus;
import com.example.airline.domain.valueObjects.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

// Reservation:
//At the heart of business rules
//Seat availability is calculated here
public interface ReservationRepository
        extends JpaRepository<Reservation,Long> {

    // no Business logic
    //just data query
    // How many active reservations are there for this seat type on this flight?
    long countByFlightAndSeatTypeAndStatus(Flight flight,
                                           SeatType seatType,
                                           ReservationStatus reservationStatus);
}
