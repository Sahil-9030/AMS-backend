package com.major.connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.dtos.AddFlightScheduleDto;
import com.major.connect.services.FlightScheduleServiceImpl;

@RestController
@RequestMapping("/auth/admin/schedule")
public class FlightScheduleController {
	
	@Autowired
	private FlightScheduleServiceImpl flightScheduleService;

	@GetMapping("/get/all")
	public ResponseEntity<Object> getAllFlightSchedule(){
		return flightScheduleService.getAllFlightSchedule();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addFlightSchedule(@RequestBody AddFlightScheduleDto dto){
		return flightScheduleService.addFlightSchedule(dto);
	}
	
}
