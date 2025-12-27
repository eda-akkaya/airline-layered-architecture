package com.example.airline.business.service;
//Why only one Service?
//Seat control + price + registration in the same transaction
// split it up, inconsistencies arise

// The service layer:
//Manages use cases
//Collects business rules in one place
//Acts as a transaction boundary
//Coordinates repositories

// Use Case: Create reservation
//Steps in order:
//Is there a flight?
//Is there a passenger?
//Is the desired seat type fully booked?
//Calculate the price
//Create reservation
//Save
//Return result

import com.example.airline.common.exception.NotFoundException;
import com.example.airline.common.exception.SeatNotAvailableException;
import com.example.airline.data.repository.FlightRepository;
import com.example.airline.data.repository.PassengerRepository;
import com.example.airline.data.repository.ReservationRepository;
import com.example.airline.domain.entities.Flight;
import com.example.airline.domain.entities.Passenger;
import com.example.airline.domain.entities.Reservation;
import com.example.airline.domain.enums.ReservationStatus;
import com.example.airline.domain.valueObjects.Money;
import com.example.airline.domain.valueObjects.SeatType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReservationService {

    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(FlightRepository flightRepository, PassengerRepository passengerRepository, ReservationRepository reservationRepository) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
    }


    @Transactional
    public Reservation createReservation(Long flightId, Long passengerId, SeatType seatType){
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()-> new NotFoundException("Flight not found!!"));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(()-> new NotFoundException("Passsenger not found!!"));

        long reservationSeatCount =
                reservationRepository.countByFlightAndSeatTypeAndStatus(
                        flight,
                        seatType,
                        ReservationStatus.CREATED
        );

        int totalSeats =
                switch (seatType){
                    case ECONOMY -> flight.getAircraft().getTotalEconomySeats();
                    case BUSINESS ->  flight.getAircraft().getTotalBusinessSeats();
                };

        if (reservationSeatCount >= totalSeats){
            throw new SeatNotAvailableException();
        }

        Money price = calculatePrice(passenger, seatType);

        Reservation reservation = new Reservation(passenger, flight, seatType, price);

        return reservationRepository.save(reservation);

    }

    private Money calculatePrice(Passenger passenger, SeatType seatType) {
        BigDecimal basePrice = BigDecimal.valueOf(1000);

        BigDecimal multiplier =
                switch (seatType){
                    case ECONOMY -> BigDecimal.ONE;
                    case BUSINESS -> BigDecimal.valueOf(1.5);
                };

        return new Money(basePrice.multiply(multiplier), "TRY");


    }
}
