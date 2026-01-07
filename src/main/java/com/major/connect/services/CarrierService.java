package com.major.connect.services;

import org.springframework.http.ResponseEntity;

import com.major.connect.dtos.CarrierDto;

public interface CarrierService {
	ResponseEntity<Object> getCarrierBycarrierId(long carrierId);
	ResponseEntity<Object> addCarrier(CarrierDto dto);
}
