package com.example.airline.data.repository;

import com.example.airline.domain.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository
        extends JpaRepository<Passenger,Long> {
}
