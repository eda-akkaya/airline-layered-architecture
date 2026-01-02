package com.example.airline.presentation.controller;
//Controller = The adapter between the external world and the system

import com.example.airline.business.service.ReservationService;
import com.example.airline.common.dto.CreateReservationRequest;
import com.example.airline.common.dto.ReservationResponse;
import com.example.airline.domain.entities.Reservation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping // Create
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody CreateReservationRequest request){
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

    @GetMapping("/{id}") // Read
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationById(id);
        ReservationResponse response =
                new ReservationResponse(
                        reservation.getId(),
                        reservation.getFlight().getFlightNumber(),
                        reservation.getSeatType(),
                        reservation.getPrice().getAmount(),
                        reservation.getPrice().getCurrency()
                );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/cancel") // Cancel
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id){
        reservationService.cancelReservation(id);
        System.out.println("The reservation with ID " + id + " has been canceled.");
        return ResponseEntity.noContent().build();
    }
}
