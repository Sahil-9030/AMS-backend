package com.major.connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.connect.models.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long>{
    
}
