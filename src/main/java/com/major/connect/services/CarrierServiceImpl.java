package com.major.connect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
=======
import org.springframework.http.HttpStatusCode;
>>>>>>> 78140cefe7efad44b6a9814f0898869b087fa4af
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.major.connect.dtos.CarrierDto;
import com.major.connect.dtos.EditCarrierDto;
import com.major.connect.models.Carrier;
import com.major.connect.repositories.CarrierRepository;

@Service
public class CarrierServiceImpl implements CarrierService{
	
	@Autowired
	private CarrierRepository carrierRepository;

	@Override
	public ResponseEntity<Object> getCarrierBycarrierId(long carrierId) {
		try {
			Carrier c1 = carrierRepository.findByCarrierId(carrierId).orElseThrow(() -> new IllegalArgumentException("No Carrier found with this carrier id"));
			if(c1 != null) {
				return ResponseEntity.status(200).body(c1);
			}
			return null;
		}catch(Exception e) {
			return ResponseEntity.status(400).body("Error Occured : " + e.getMessage());
		}
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
			
			return ResponseEntity.status(HttpStatus.FOUND).body(carrierRepository.save(c2));
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

			return ResponseEntity.status(HttpStatus.OK).body(c1);

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching the list of carriers " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> deleteCarrier(long carrierId) {
		try{

			Carrier c1 = carrierRepository.findByCarrierId(carrierId).orElseThrow(() -> new IllegalArgumentException("No Carrier exist with this carrier id"));

			if(c1 == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No carriers to delete");
			}

			carrierRepository.deleteById(carrierId);
			
			return ResponseEntity.status(HttpStatus.OK).body("Carrier with " + carrierId + " successfully deleted");

		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while deleting the carrier " + e.getMessage());
		}
	}

}
