package com.major.connect.services;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.major.connect.dtos.AddFlightDto;
import com.major.connect.dtos.EditFlightRequestDTO;

public interface FlightService {
	ResponseEntity<Object>addFlight(AddFlightDto dto);
	ResponseEntity<Object> getFlightById(long flightId);
	ResponseEntity<Object> getAllFlights();
	ResponseEntity<Object> editFlightById( EditFlightRequestDTO request);
	ResponseEntity<Object> deleteFlightById(long flightId);

	ResponseEntity<Object> getFlightForSpecificRouteAndDate(String origin, String destination, Date travelDate);
	
	ResponseEntity<Object> cancelFlight(long flightId, Date travelDate);
}
