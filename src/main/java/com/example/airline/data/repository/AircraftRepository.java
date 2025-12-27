package com.example.airline.data.repository;

import com.example.airline.domain.entities.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository
        extends JpaRepository<Aircraft,Long> {
}
