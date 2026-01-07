package com.major.connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.config.UserInfoUserDetailsService;
import com.major.connect.dtos.RegisterRequest;
import com.major.connect.dtos.loginDto;
import com.major.connect.models.UserInfo;
import com.major.connect.repositories.UserRepository;
import com.major.connect.utils.jwtAuthService;

@RestController
@RequestMapping("/public")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserInfoUserDetailsService userDetailsService;
	
	@Autowired
	jwtAuthService jwtService;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private UserRepository userRepository;

	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginDto request) {

        // 1. Authenticate username & password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2. Load user details
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        // 3. Generate JWT token
        String token = jwtService.generateToken(userDetails);

        // 4. Return token
        return ResponseEntity.ok(token);
    }
	
	

	    @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

	        // check if email already exists
	        if (userRepository.existsByEmail(request.getEmail())) {
	            return ResponseEntity
	                    .status(HttpStatus.CONFLICT)
	                    .body("Email already exists");
	        }

	        UserInfo user = new UserInfo();
	        user.setUsername(request.getUsername());
	        user.setEmail(request.getEmail());
	        user.setPassword(passwordEncoder.encode(request.getPassword()));
	        user.setRole(request.getRole());
	        user.setAddress1(request.getAddress1());
	        user.setAddress2(request.getAddress2());
	        user.setCity(request.getCity());
	        user.setCountry(request.getCountry());
	        user.setDob(request.getDob());
	        user.setState(request.getState());
	        user.setPhone(request.getPhone());
	        user.setUser_category(request.getUserCategory());
	        user.setZip_code(request.getZipCode());

	        

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(userRepository.save(user));
	    }
}
