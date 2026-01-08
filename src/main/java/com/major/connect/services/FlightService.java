package com.major.connect.services;

import org.springframework.http.ResponseEntity;

import com.major.connect.dtos.AddFlightDto;

public interface FlightService {
	ResponseEntity<Object>addFlight(AddFlightDto dto);
}
