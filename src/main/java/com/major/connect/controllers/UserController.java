package com.major.connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.services.UserServiceImpl;

@RestController
@RequestMapping("/auth/user")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/get/{email}")
	public ResponseEntity<Object> getUserByEmail(@PathVariable String email){
		return userServiceImpl.getUserByEmail(email);
	}
	
	@GetMapping("/get/all-customers")
	public ResponseEntity<Object> getAllUser(){
		return null;
	}
}
