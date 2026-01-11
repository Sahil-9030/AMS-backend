package com.major.connect.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.dtos.AddFlightDto;
import com.major.connect.services.FlightServiceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/get/all-flights")
	public ResponseEntity<Object> getAllFlights(){
		return flightService.getAllFlights();
	}

	@GetMapping("/get/flight/{flightId}")
	public ResponseEntity<Object> getFlightById(@PathVariable long flightId){
		return flightService.getFlightById(flightId);
	}

	@PutMapping("/edit/flight/{flightId}")
	public ResponseEntity<Object> editFlightDetails(@PathVariable long flightId){
		return flightService.editFlightById(flightId);
	}

	@DeleteMapping("/delete/flight/{flightId}")
	public ResponseEntity<Object> deleteFlightById(@PathVariable long flightId){
		return flightService.deleteFlightById(flightId);
	}

	@GetMapping("/get/flight/")
	public ResponseEntity<Object> getFlightForSpecificRouteAndDate(@RequestParam String origin, @RequestParam String destination, @RequestParam Date travelDate){
		return null;
	}
	
}
