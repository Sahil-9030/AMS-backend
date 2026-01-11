package com.major.connect.services;

import org.springframework.http.ResponseEntity;

import com.major.connect.dtos.AddFlightBookingDto;

public interface FlightBookingService {
    ResponseEntity<Object> addFlightBooking(AddFlightBookingDto dto);
    ResponseEntity<Object> cancelMyFlightBooking(long bookingId);
}
