package com.example.airline.common.exception;

public class SeatNoAvailableException extends RuntimeException {
    public SeatNoAvailableException(String message) {
        super(message);
    }
}
