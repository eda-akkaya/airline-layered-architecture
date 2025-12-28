package com.example.airline.common.dto;

import com.example.airline.domain.valueObjects.SeatType;

public class CreateReservationRequest {

    private Long flightId;
    private Long passengerId;
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
