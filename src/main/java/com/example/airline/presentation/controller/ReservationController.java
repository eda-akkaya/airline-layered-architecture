package com.example.airline.presentation.controller;
//Controller = The adapter between the external world and the system

import com.example.airline.business.service.ReservationService;
import com.example.airline.common.dto.CreateReservationRequest;
import com.example.airline.common.dto.ReservationResponse;
import com.example.airline.domain.entities.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody CreateReservationRequest request){
        Reservation reservation = reservationService
                .createReservation(
                        request.getFlightId(),
                        request.getPassengerId(),
                        request.getSeatType()
        );

        ReservationResponse response = new ReservationResponse(
                reservation.getId(),
                reservation.getFlight().getFlightNumber(),
                reservation.getSeatType(),
                reservation.getPrice().getAmount(),
                reservation.getPrice().getCurrency()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
