package com.major.connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.dtos.AddFlightBookingDto;
import com.major.connect.services.FlightBookingServiceImpl;

@RestController
@RequestMapping("/auth/admin")
public class FlightBookingController {
	
	@Autowired
	FlightBookingServiceImpl flightBookingServiceImpl;
    
    @PostMapping("/add/booking")
    public ResponseEntity<Object> addFlightBooking(@RequestBody AddFlightBookingDto dto){
        return flightBookingServiceImpl.addFlightBooking(dto);
    }
}
