package com.major.connect.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.major.connect.models.UserInfo;
import com.major.connect.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<Object> getUserByEmail(String email) {
		try {
			
			List<UserInfo> list = userRepository.findAll();
			
			UserInfo user = list.stream().filter(f-> f.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
			
//			UserInfo user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("No User found with this email " + email));
			
			if(user == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user found with this email");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(user);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while getting user " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllUser() {
		try {
			List<UserInfo> list = userRepository.findAll();
			
			List<UserInfo> customersList = list.stream().filter(f-> f.getUser_category().equalsIgnoreCase("customer")).collect(Collectors.toList());
			
//			UserInfo user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("No User found with this email " + email));
			
			if(customersList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user found with this email");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(customersList);
		}catch(Exception e) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while getting list of customers");
		}
	}

}
