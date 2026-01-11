package com.major.connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.connect.models.FlightSchedule;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule,Long> {
    
}
