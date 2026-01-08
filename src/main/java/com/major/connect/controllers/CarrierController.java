package com.major.connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.dtos.CarrierDto;
import com.major.connect.dtos.EditCarrierDto;
import com.major.connect.services.CarrierServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/auth/admin")
public class CarrierController {
	
	@Autowired
	CarrierServiceImpl carrierServiceImpl;
	
	@GetMapping("/search/carrier/{carrierId}")
	public ResponseEntity<Object> getCarrierById(@PathVariable long carrierId){
		return carrierServiceImpl.getCarrierBycarrierId(carrierId);
	}
	
	@PostMapping("/add/carrier")
	public ResponseEntity<Object> addCarrier(@RequestBody CarrierDto dto){
		return carrierServiceImpl.addCarrier(dto);
	}

	@PutMapping("/edit/carrier/{carrierId}")
	public ResponseEntity<Object> updateCarrier(@PathVariable long carrierId, @RequestBody EditCarrierDto dto) {
		return carrierServiceImpl.editCarrier(carrierId,dto);
	}

	@GetMapping("/get/carrier/all")
	public ResponseEntity<Object> getAllCarrier(){
		return carrierServiceImpl.getAllCarrier();
	}

	@DeleteMapping("/delete/carrier/{carrierId}")
	public ResponseEntity<Object> deleteCarrier(@PathVariable long carrierId){
		return carrierServiceImpl.deleteCarrier(carrierId);
	}
} 
