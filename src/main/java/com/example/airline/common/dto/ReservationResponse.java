package com.example.airline.common.dto;

import com.example.airline.domain.valueObjects.SeatType;

import java.math.BigDecimal;

public class ReservationResponse {

    private Long reservationId;
    private String flightNumber;
    private SeatType seatType;
    private BigDecimal price;
    private String currency;

    public ReservationResponse(Long reservationId, String flightNumber, SeatType seatType, BigDecimal price, String currency) {
        this.reservationId = reservationId;
        this.flightNumber = flightNumber;
        this.seatType = seatType;
        this.price = price;
        this.currency = currency;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
