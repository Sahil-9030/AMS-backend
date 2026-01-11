package com.major.connect.services;

import org.springframework.http.ResponseEntity;

import com.major.connect.dtos.CarrierDto;
import com.major.connect.dtos.EditCarrierDto;

public interface CarrierService {
	ResponseEntity<Object> getCarrierBycarrierId(long carrierId);
	ResponseEntity<Object> addCarrier(CarrierDto dto);
	ResponseEntity<Object> editCarrier(long carrierId, EditCarrierDto dto);
	ResponseEntity<Object> getAllCarrier();
	ResponseEntity<Object> deleteCarrier(long carrierId);

}
