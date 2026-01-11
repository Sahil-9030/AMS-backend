package com.major.connect.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.major.connect.dtos.AddFlightDto;
import com.major.connect.models.Carrier;
import com.major.connect.models.Flight;
import com.major.connect.models.FlightBooking;
import com.major.connect.models.FlightSchedule;
import com.major.connect.repositories.CarrierRepository;
import com.major.connect.repositories.FlightBookingRepository;
import com.major.connect.repositories.FlightRepository;
import com.major.connect.repositories.FlightScheduleRepository;

@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	CarrierRepository carrierRepository;

	@Autowired
	FlightBookingRepository flightBookingRepository;

	@Autowired
	FlightScheduleRepository flightScheduleRepository;

	@Override
	public ResponseEntity<Object> addFlight(AddFlightDto dto) {
		try {
			Carrier c1 = carrierRepository.findByCarrierId(dto.getCarrierId()).orElseThrow(() -> new IllegalArgumentException("There is No carrier with this carrier id"));
			
			if(c1 == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No carrier");
			}
			Flight f1 = new Flight();
			f1.setCarrier(c1);
			f1.setAirfare(dto.getAirfare());
			f1.setDestination(dto.getDestination());

			List<FlightBooking> list = new ArrayList<>();

			for (Long bookingId : dto.getBookingIds()) {
				Optional<FlightBooking> fb = flightBookingRepository.findById(bookingId);
				if(fb.isPresent()){
					list.add(fb.get());
				}
			}
			f1.setBookings(list);

			Optional<FlightSchedule> fs = flightScheduleRepository.findById(dto.getScheduleId());
			
			if(fs.isPresent()){
				f1.setSchedule(fs.get());
			}

			f1.setSeat_capacity_business(dto.getSeatCapacityBusiness());
			f1.setSeat_capacity_economy(dto.getSeatCapacityEconomy());
			f1.setSeat_capacity_executive(dto.getSeatCapacityExecutive());

			return ResponseEntity.status(HttpStatus.CREATED).body(flightRepository.save(f1));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while creating flight " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getFlightById(long flightId) {
		try {
			Flight f1 = flightRepository.findById(flightId).orElseThrow(() -> new IllegalArgumentException("No Flight found with this flight id"));
			if(f1 != null) {
				return ResponseEntity.status(200).body(f1);
			}
			return null;
		}catch(Exception e) {
			return ResponseEntity.status(400).body("Error Occured : " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllFlights() {
		try{
			List<Flight> f1 = flightRepository.findAll();

			if(f1.isEmpty()){
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("No Flights to show");
			}

			return ResponseEntity.status(HttpStatus.OK).body(f1);

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching the list of flights " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> editFlightById(long flightId) {
		// try{

		// 	Carrier c2 = carrierRepository.findByCarrierId(carrierId).orElseThrow(() -> new IllegalArgumentException("No Carrier found with this carrier id "));
			
		// 	if(c2 == null){
		// 		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No carrier found with this carrier id");
		// 	}	

			
			
		// 	return ResponseEntity.status(HttpStatus.FOUND).body(carrierRepository.save(c2));
		// }catch(Exception e){
		// 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while updating the carrier " + e.getMessage());
		// }
		return null;
	}

	@Override
	public ResponseEntity<Object> deleteFlightById(long flightId) {
		try{

			Flight f1 = flightRepository.findById(flightId).orElseThrow(() -> new IllegalArgumentException("No Carrier exist with this flight id"));

			if(f1 == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No flight to delete");
			}

			flightRepository.deleteById(flightId);
			
			return ResponseEntity.status(HttpStatus.OK).body("Carrier with " + flightId + " successfully deleted");

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while deleting the flight " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getFlightForSpecificRouteAndDate(String origin, String destination, Date travelDate) {
		try{
			List<Flight> f1 = flightRepository.findAll();
			List<Flight> list = f1.stream().filter(f-> f.getOrigin().toLowerCase().equals(origin) && 
													f.getDestination().toLowerCase().equals(destination) &&
													f.getSchedule().getDateOfTravel().equals(travelDate))
												.collect(Collectors.toList());	
			
			if(list.isEmpty()){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No flights found for this route");
			}

			return ResponseEntity.status(HttpStatus.OK).body(list);

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while geting flight for this route " + e.getMessage());
		}
	}

}
