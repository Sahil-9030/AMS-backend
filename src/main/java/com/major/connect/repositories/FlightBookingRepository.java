package com.major.connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.connect.models.FlightBooking;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
    
}
