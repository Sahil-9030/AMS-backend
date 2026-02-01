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
import com.major.connect.dtos.EditFlightRequestDTO;
import com.major.connect.dtos.FlightResponseDTO;
import com.major.connect.models.Carrier;
import com.major.connect.models.Flight;
import com.major.connect.models.FlightBooking;
import com.major.connect.models.FlightSchedule;
import com.major.connect.repositories.CarrierRepository;
import com.major.connect.repositories.FlightBookingRepository;
import com.major.connect.repositories.FlightRepository;
import com.major.connect.repositories.FlightScheduleRepository;

import jakarta.transaction.Transactional;

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
	@Transactional
	public ResponseEntity<Object> addFlight(AddFlightDto dto) {
		try {
			Carrier c1 = carrierRepository.findByCarrierId(dto.getCarrierId()).orElseThrow(() -> new IllegalArgumentException("There is No carrier with this carrier id"));
			
			if(c1 == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No carrier");
			}
			Flight f1 = new Flight();
			f1.setCarrier(c1);
			f1.setAirfare(dto.getAirfare());
			f1.setOrigin(dto.getOrigin());
			f1.setDestination(dto.getDestination());

			List<FlightBooking> list = new ArrayList<>();

			if(dto.getBookingIds() != null) {
				for (Long bookingId : dto.getBookingIds()) {
					Optional<FlightBooking> fb = flightBookingRepository.findById(bookingId);
					if(fb.isPresent()){
						list.add(fb.get());
					}
				}
			}
			
			f1.setBookings(list);

//			Optional<FlightSchedule> fs = flightScheduleRepository.findById(dto.getScheduleId());
//			
//			if(fs.isPresent()){
//				f1.setSchedule(fs.get());
//			}

			f1.setSeat_capacity_business(dto.getSeatCapacityBusiness());
			f1.setSeat_capacity_economy(dto.getSeatCapacityEconomy());
			f1.setSeat_capacity_executive(dto.getSeatCapacityExecutive());

			flightRepository.save(f1);
			return ResponseEntity.status(HttpStatus.CREATED).body(mapFlightToDTO(f1));
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
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Flights to show");
			}

			List<FlightResponseDTO> response = f1.stream()
	                .map(this::mapFlightToDTO)
	                .toList();

	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(response);

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching the list of flights " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> editFlightById( EditFlightRequestDTO request) {
		 try{

			 Flight f1 = flightRepository.findById(request.getFlightId()).orElse(null);
			 
			 if(f1 == null) {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight not founf for this id");
			 }
			 
//			 Flight f1 = new Flight();
			 f1.setAirfare(request.getAirfare());
			 f1.setDestination(request.getDestination());
			 f1.setOrigin(request.getOrigin());
			 f1.setSeat_capacity_business(request.getSeatCapacityBusiness());
			 f1.setSeat_capacity_economy(request.getSeatCapacityEconomy());
			 f1.setSeat_capacity_executive(request.getSeatCapacityExecutive());
		 				
			 flightRepository.save(f1);
		 	return ResponseEntity.status(HttpStatus.FOUND).body(mapFlightToDTO(f1));
		 }catch(Exception e){
		 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while updating the carrier " + e.getMessage());
		 }
	}
	
	private FlightResponseDTO mapFlightToDTO(Flight flight) {

	    FlightResponseDTO dto = new FlightResponseDTO();

	    dto.setFlightId(flight.getFlightId());

	    // carrier safe fields only
	    dto.setCarrierId(flight.getCarrier().getCarrierId());
	    dto.setCarrierName(flight.getCarrier().getCarrierName());

	    dto.setOrigin(flight.getOrigin());
	    dto.setDestination(flight.getDestination());
	    dto.setAirfare(flight.getAirfare());

	    dto.setSeatCapacityBusiness(flight.getSeat_capacity_business());
	    dto.setSeatCapacityEconomy(flight.getSeat_capacity_economy());
	    dto.setSeatCapacityExecutive(flight.getSeat_capacity_executive());

	    return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteFlightById(long flightId) {
		try{
			
			Flight f1 = flightRepository.findById(flightId).orElseThrow(() -> new IllegalArgumentException("No Flight exist with this flight id"));
			
			if(f1 == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No flight to delete");
			}
			
			List<FlightSchedule> list = flightScheduleRepository.findAll();
			
			FlightSchedule fs = list.stream().filter(f -> f.getFlight().getFlightId() == flightId).findFirst().orElse(null);
			
			if(fs != null) {
				flightScheduleRepository.delete(fs);
			}
			

			f1.getBookings().forEach(booking -> booking.setBookingStatus("Cancelled"));
			
			flightRepository.deleteById(flightId);
			
			return ResponseEntity.status(HttpStatus.OK).body("Flight with " + flightId + " successfully deleted");

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while deleting the flight " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getFlightForSpecificRouteAndDate(String origin, String destination, Date travelDate) {
		try{
			List<FlightSchedule> scheduledList = flightScheduleRepository.findAll();
			
			if(scheduledList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no flights scheduled");
			}
			
			List<FlightSchedule> list = scheduledList.stream().filter(schedule -> schedule.getFlight().getDestination().equalsIgnoreCase(destination)
														&& schedule.getFlight().getOrigin().equalsIgnoreCase(origin)
														&& schedule.getDateOfTravel() == travelDate)
														.collect(Collectors.toList());
			
			if(list.isEmpty()){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No flights found for this route");
			}

			return ResponseEntity.status(HttpStatus.OK).body(list);

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while geting flight for this route " + e.getMessage());
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Object> cancelFlight(long flightId, Date travelDate) {
		try {
			List<FlightSchedule> scheduledList = flightScheduleRepository.findAll();
			
			if(scheduledList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no flights scheduled");
			}
			
			
			FlightSchedule scheduledFlightFound = scheduledList.stream().filter(schedule -> schedule.getDateOfTravel() == travelDate
																&& schedule.getFlight().getFlightId() == flightId).findFirst().orElse(null);
					
			
			if(scheduledFlightFound == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No flight with the flight id " + flightId + "is scheduled");
			}
			
			
			List<FlightBooking> list = scheduledFlightFound.getFlight().getBookings();
			
			for (FlightBooking fb : list) {
				fb.setBookingStatus("CANCELED");
			}
			
			long totalRefundAmount = 0;
			
			for(FlightBooking fb:list) {
				
				if(fb.getDateOfTravel().equals( new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) || 
						fb.getDateOfTravel().before( new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))) {
					totalRefundAmount += fb.getBookingAmount()+10/100;
					fb.setBookingAmount(fb.getBookingAmount()+10/100);
				}
				
				totalRefundAmount += fb.getBookingAmount();
				fb.setBookingAmount(fb.getBookingAmount());
			}
			
			return ResponseEntity.status(HttpStatus.OK).body("Flight with " + flightId + " is successfully canceled " + totalRefundAmount);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while cancelling the flight with flight id :" + flightId);
		}
	}

}
