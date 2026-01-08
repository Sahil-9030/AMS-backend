package com.major.connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.major.connect.dtos.AddFlightDto;
import com.major.connect.models.Carrier;
import com.major.connect.models.Flight;
import com.major.connect.repositories.CarrierRepository;
import com.major.connect.repositories.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	CarrierRepository carrierRepository;

	@Override
	public ResponseEntity<Object> addFlight(AddFlightDto dto) {
		try {
			Carrier c1 = carrierRepository.findByCarrierId(dto.getCarrierId()).orElseThrow(() -> new IllegalArgumentException("There is No carrier with this carrier id"));
			
			Flight f1 = new Flight();
//			f1.setCarrier(dto.getCarrierId());
			return null;
		}catch(Exception e) {
			return null;
		}
	}

}
