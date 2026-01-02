package com.example.airline.common.dto;

import com.example.airline.domain.valueObjects.SeatType;
import jakarta.validation.constraints.NotNull;

public class CreateReservationRequest {

    @NotNull
    private Long flightId;
    @NotNull
    private Long passengerId;
    @NotNull
    private SeatType seatType;

    public Long getFlightId() {
        return flightId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public SeatType getSeatType() {
        return seatType;
    }
}
