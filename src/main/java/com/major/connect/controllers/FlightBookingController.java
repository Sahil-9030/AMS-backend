package com.major.connect.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.dtos.AddFlightBookingDto;

@RestController
@RequestMapping("/auth/admin")
public class FlightBookingController {
    
    @PostMapping("/add/booking")
    public ResponseEntity<Object> addFlightBooking(@RequestBody AddFlightBookingDto dto){
        return null;
    }
}
