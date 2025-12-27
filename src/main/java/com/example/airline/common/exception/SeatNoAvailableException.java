package com.example.airline.common.exception;

public class SeatNoAvailableException extends RuntimeException {
    public SeatNoAvailableException() {
        super("Requested seat type is not available for this flight !!");
    }
}
