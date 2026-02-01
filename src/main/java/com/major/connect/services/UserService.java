package com.major.connect.services;

import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<Object> getUserByEmail(String email);
	ResponseEntity<Object> getAllUser();
}
