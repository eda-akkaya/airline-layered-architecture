package com.example.airline.common.exception;

public class SeatNotAvailableException extends RuntimeException {
    public SeatNotAvailableException() {
        super("Requested seat type is not available for this flight !!");
    }
}
