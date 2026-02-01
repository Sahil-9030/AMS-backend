package com.major.connect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.major.connect.controllers.FlightScheduleController;
import com.major.connect.dtos.CarrierDto;
import com.major.connect.dtos.CarrierListDTO;
import com.major.connect.dtos.CarrierResponseDTO;
import com.major.connect.dtos.EditCarrierDto;
import com.major.connect.dtos.FlightResponseDTO;
import com.major.connect.models.Carrier;
import com.major.connect.models.Flight;
import com.major.connect.models.FlightSchedule;
import com.major.connect.repositories.CarrierRepository;
import com.major.connect.repositories.FlightRepository;
import com.major.connect.repositories.FlightScheduleRepository;

import jakarta.transaction.Transactional;

@Service
public class CarrierServiceImpl implements CarrierService{
	
	@Autowired
	private CarrierRepository carrierRepository;
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private FlightScheduleRepository flightScheduleRepository;

	@Override
	public ResponseEntity<Object> getCarrierBycarrierId(long carrierId) {
		try {
			Carrier c1 = carrierRepository.findByCarrierId(carrierId).orElseThrow(() -> new IllegalArgumentException("No Carrier found with this carrier id"));
			if(c1 != null) {
				return ResponseEntity.status(200).body(mapCarrierToDTO(c1));
			}
			return ResponseEntity.status(400).body("No carrier found for this id");
		}catch(Exception e) {
			return ResponseEntity.status(400).body("Error Occured : " + e.getMessage());
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
	
	private CarrierResponseDTO mapCarrierToDTO(Carrier carrier) {

	    CarrierResponseDTO dto = new CarrierResponseDTO();

	    dto.setCarrierId(carrier.getCarrierId());
	    dto.setCarrierName(carrier.getCarrierName());

	    dto.setDiscount_percentage_thirty_days_advance_booking(
	            carrier.getDiscount_percentage_thirty_days_advance_booking());

	    dto.setDiscount_percentage_sixty_days_advance_booking(
	            carrier.getDiscount_percentage_sixty_days_advance_booking());

	    dto.setDiscount_percentage_nintey_days_advance_booking(
	            carrier.getDiscount_percentage_nintey_days_advance_booking());

	    dto.setBulk_booking_discount(carrier.getBulk_booking_discount());

	    dto.setRefund_percentage_for_cancellation_two_days_before_travel_date(
	            carrier.getRefund_percentage_for_cancellation_two_days_before_travel_date());

	    dto.setRefund_percentage_for_cancellation_ten_days_before_travel_date(
	            carrier.getRefund_percentage_for_cancellation_ten_days_before_travel_date());

	    dto.setRefund_percentage_for_cancellation_twenty_days_before_travel_date(
	            carrier.getRefund_percentage_for_cancellation_twenty_days_before_travel_date());

	    dto.setSilver_user_discount(carrier.getSilver_user_discount());
	    dto.setGold_user_discount(carrier.getGold_user_discount());
	    dto.setPlatinum_user_discount(carrier.getPlatinum_user_discount());
	    
	    dto.setFlights(
	        carrier.getFlights()
	               .stream()
	               .map(this::mapFlightToDTO)
	               .toList()
	    );

	    return dto;
	}




	@Override
	public ResponseEntity<Object> addCarrier(CarrierDto dto) {
		
		try {
			
			System.err.println("carrier " + carrierRepository.findByCarrierName(dto.getCarrierName()));
			if(!carrierRepository.findByCarrierName(dto.getCarrierName()).isEmpty()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Carrier with this carrier name already exist");
			}
			
			Carrier c1 = new Carrier();
			c1.setCarrierName(dto.getCarrierName());
			c1.setRefund_percentage_for_cancellation_twenty_days_before_travel_date(dto.getRefundPercentageTwentyDaysBeforeTravelDate());
			c1.setRefund_percentage_for_cancellation_ten_days_before_travel_date(dto.getRefundPercentageTenDaysBeforeTravelDate());
			c1.setRefund_percentage_for_cancellation_two_days_before_travel_date(dto.getRefundPercentageTwoDaysBeforeTravelDate());
			c1.setDiscount_percentage_nintey_days_advance_booking(dto.getDiscountPercentageNinetyDaysAdvanceBooking());
			c1.setDiscount_percentage_sixty_days_advance_booking(dto.getDiscountPercentageSixtyDaysAdvanceBooking());
			c1.setDiscount_percentage_thirty_days_advance_booking(dto.getDiscountPercentageThirtyDaysAdvanceBooking());
			c1.setBulk_booking_discount(dto.getBulkBookingDiscount());
			c1.setGold_user_discount(dto.getGoldUserDiscount());
			c1.setPlatinum_user_discount(dto.getPlatinumUserDiscount());
			c1.setSilver_user_discount(dto.getSilverUserDiscount());
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(carrierRepository.save(c1));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Occured while adding new Carrier" + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> editCarrier(long carrierId,EditCarrierDto dto) {
		try{
			Carrier c2 = carrierRepository.findByCarrierId(carrierId).orElseThrow(() -> new IllegalArgumentException("No Carrier found with this carrier id "));
			
			if(c2 == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No carrier found with this carrier id");
			}
			System.out.print("comingggggggggggggg");

			c2.setBulk_booking_discount(dto.getBulkBookingDiscount());
			c2.setDiscount_percentage_nintey_days_advance_booking(dto.getDiscountPercentageNinetyDaysAdvanceBooking());
			c2.setDiscount_percentage_thirty_days_advance_booking(dto.getDiscountPercentageThirtyDaysAdvanceBooking());
			c2.setDiscount_percentage_sixty_days_advance_booking(dto.getDiscountPercentageSixtyDaysAdvanceBooking());
			c2.setRefund_percentage_for_cancellation_ten_days_before_travel_date(dto.getRefundPercentageTenDaysBeforeTravelDate());
			c2.setRefund_percentage_for_cancellation_twenty_days_before_travel_date(dto.getRefundPercentageTwentyDaysBeforeTravelDate());
			c2.setRefund_percentage_for_cancellation_two_days_before_travel_date(dto.getRefundPercentageTwoDaysBeforeTravelDate());
			c2.setGold_user_discount(dto.getGoldUserDiscount());
			c2.setPlatinum_user_discount(dto.getPlatinumUserDiscount());
			c2.setSilver_user_discount(dto.getSilverUserDiscount());
			
			return ResponseEntity.status(HttpStatus.OK).body(carrierRepository.save(c2));
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while updating the carrier " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllCarrier() {
		try{

			List<Carrier> c1 = carrierRepository.findAll();

			if(c1.isEmpty()){
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("No Carrier to show");
			}
			
			List<CarrierResponseDTO> response =
					c1.stream()
			                .map(carrier -> {
			                    CarrierResponseDTO dto = new CarrierResponseDTO();
			                    dto.setCarrierId(carrier.getCarrierId());
			                    dto.setCarrierName(carrier.getCarrierName());
			                    dto.setDiscount_percentage_thirty_days_advance_booking(
			            	            carrier.getDiscount_percentage_thirty_days_advance_booking());

			            	    dto.setDiscount_percentage_sixty_days_advance_booking(
			            	            carrier.getDiscount_percentage_sixty_days_advance_booking());

			            	    dto.setDiscount_percentage_nintey_days_advance_booking(
			            	            carrier.getDiscount_percentage_nintey_days_advance_booking());

			            	    dto.setBulk_booking_discount(carrier.getBulk_booking_discount());

			            	    dto.setRefund_percentage_for_cancellation_two_days_before_travel_date(
			            	            carrier.getRefund_percentage_for_cancellation_two_days_before_travel_date());

			            	    dto.setRefund_percentage_for_cancellation_ten_days_before_travel_date(
			            	            carrier.getRefund_percentage_for_cancellation_ten_days_before_travel_date());

			            	    dto.setRefund_percentage_for_cancellation_twenty_days_before_travel_date(
			            	            carrier.getRefund_percentage_for_cancellation_twenty_days_before_travel_date());

			            	    dto.setSilver_user_discount(carrier.getSilver_user_discount());
			            	    dto.setGold_user_discount(carrier.getGold_user_discount());
			            	    dto.setPlatinum_user_discount(carrier.getPlatinum_user_discount());
			                    return dto;
			                })
			                .toList();


			return ResponseEntity.status(HttpStatus.OK).body(response);

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching the list of carriers " + e.getMessage());
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteCarrier(long carrierId) {
		try{

			Carrier c1 = carrierRepository.findByCarrierId(carrierId).orElseThrow(() -> new IllegalArgumentException("No Carrier exist with this carrier id"));

			if(c1 == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No carriers to delete");
			}

			c1.getFlights().forEach(flight -> flight.getBookings().forEach( booking -> booking.setBookingStatus("Cancelled")));
			
			List<FlightSchedule> list = flightScheduleRepository.findAll();
			
//			FlightSchedule fs = list.stream().filter(f -> c1.getFlights().contains(f.getFlight()) ? 
//									c1.getFlights().forEach(flight -> {
//										flight.getBookings()
//											  .forEach(booking -> booking.setBookingStatus("Cancelled")
//													  return booking;
//											  })
//											:"").findFirst().orElse(null);
			FlightSchedule fs = list.stream()
				    .filter(f -> c1.getFlights().contains(f.getFlight()))
				    .findFirst()
				    .orElse(null);

			if (fs != null) {
			    fs.getFlight()
			      .getBookings()
			      .forEach(b -> b.setBookingStatus("Cancelled"));
			}
			flightScheduleRepository.delete(fs);
			c1.getFlights().forEach(flight -> flightRepository.delete(flight));
			carrierRepository.deleteById(carrierId);
			
			return ResponseEntity.status(HttpStatus.OK).body("Carrier with " + carrierId + " successfully deleted");

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while deleting the carrier " + e.getMessage());
		}
	}

}
