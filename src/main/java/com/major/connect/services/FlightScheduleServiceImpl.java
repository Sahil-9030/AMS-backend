package com.major.connect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.major.connect.dtos.AddFlightScheduleDto;
import com.major.connect.dtos.FlightScheduleResponseDTO;
import com.major.connect.models.Flight;
import com.major.connect.models.FlightBooking;
import com.major.connect.models.FlightSchedule;
import com.major.connect.repositories.FlightRepository;
import com.major.connect.repositories.FlightScheduleRepository;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService{
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	FlightScheduleRepository flightScheduleRepository;

	@Override
	public ResponseEntity<Object> addFlightSchedule(AddFlightScheduleDto dto) {
		try {
			
			Flight f = flightRepository.findById(dto.getFlightId()).orElse(null);
			
			if(f == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no flight associated with this flight id: " + dto.getFlightId());
			}
			
			int countForEconomyBooking = 0;
			int countForBusinessBooking = 0;
			int countForExecutiveBooking = 0;
			
			for (FlightBooking booking : f.getBookings()) {
				
				if(booking.getBookingStatus().equalsIgnoreCase("Booked") &&
						booking.getSeatCategory().equalsIgnoreCase("business")) {
					countForBusinessBooking += booking.getNoOfSeats();
				}
				
				if(booking.getBookingStatus().equalsIgnoreCase("Booked") &&
						booking.getSeatCategory().equalsIgnoreCase("economy")) {
					countForEconomyBooking += booking.getNoOfSeats();
				}
				
				if(booking.getBookingStatus().equalsIgnoreCase("Booked") &&
						booking.getSeatCategory().equalsIgnoreCase("executive")) {
					countForExecutiveBooking += booking.getNoOfSeats();
				}
				
			}
			
			
			FlightSchedule fs = new FlightSchedule();
			
			fs.setDateOfTravel(dto.getDateOfTravel());
			fs.setFlight(f);
			fs.setEconomyClassBookedCount(countForEconomyBooking);
			fs.setBusinessClassBookedCount(countForBusinessBooking);
			fs.setExecutiveClassBookedCount(countForExecutiveBooking);
			
			flightScheduleRepository.save(fs);
			return ResponseEntity.status(HttpStatus.CREATED).body(mapScheduleToDTO(fs));
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while adding flight schedule " + e.getMessage());
		}
	}

	private FlightScheduleResponseDTO mapScheduleToDTO(FlightSchedule schedule) {

	    FlightScheduleResponseDTO dto = new FlightScheduleResponseDTO();

	    dto.setFlightScheduleId(schedule.getFlightScheduleId());

	    // Flight details (safe)
	    dto.setFlightId(schedule.getFlight().getFlightId());
	    dto.setOrigin(schedule.getFlight().getOrigin());
	    dto.setDestination(schedule.getFlight().getDestination());

	    // Carrier info (safe)
	    dto.setCarrierName(
	        schedule.getFlight().getCarrier().getCarrierName()
	    );

	    dto.setDateOfTravel(schedule.getDateOfTravel());

	    dto.setBusinessClassBookedCount(
	        schedule.getBusinessClassBookedCount()
	    );

	    dto.setEconomyClassBookedCount(
	        schedule.getEconomyClassBookedCount()
	    );

	    dto.setExecutiveClassBookedCount(
	        schedule.getExecutiveClassBookedCount()
	    );

	    return dto;
	}

	
	@Override
	public ResponseEntity<Object> getAllFlightSchedule() {
		try {
			List<FlightSchedule> list = flightScheduleRepository.findAll();
			
			if(list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no flights scheduled");
			}
			
			List<FlightScheduleResponseDTO> scheduledList = list.stream().map(this::mapScheduleToDTO).toList();
			
			return ResponseEntity.status(HttpStatus.OK).body(scheduledList);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while getting all the scheduled flights");
		}
	}

}
