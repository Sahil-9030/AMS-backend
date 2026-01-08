package com.major.connect.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.dtos.AddFlightDto;
import com.major.connect.models.Flight;
import com.major.connect.services.FlightServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth/admin")
public class FlightController {
	
	@Autowired
	FlightServiceImpl flightService;
    
	@PostMapping("/add/flight")
	public ResponseEntity<Object> addFlight(@RequestBody AddFlightDto dto) {
		return flightService.addFlight(dto);
	}
	
}
