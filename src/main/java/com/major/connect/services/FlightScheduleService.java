package com.major.connect.services;

import org.springframework.http.ResponseEntity;

import com.major.connect.dtos.AddFlightScheduleDto;

public interface FlightScheduleService {

	ResponseEntity<Object>addFlightSchedule(AddFlightScheduleDto dto);
	ResponseEntity<Object>getAllFlightSchedule();
}
