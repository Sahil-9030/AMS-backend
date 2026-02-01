package com.major.connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.major.connect.config.UserInfoUserDetailsService;
import com.major.connect.dtos.LoginResponse;
import com.major.connect.dtos.RegisterRequest;
import com.major.connect.dtos.loginDto;
import com.major.connect.models.RefreshToken;
import com.major.connect.models.UserInfo;
import com.major.connect.repositories.RefreshTokenRepository;
import com.major.connect.repositories.UserRepository;
import com.major.connect.services.RefreshTokenService;
import com.major.connect.utils.jwtAuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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

	 @Autowired
	 private RefreshTokenService refreshTokenService;
	 
	 @Autowired
	 private RefreshTokenRepository refreshTokenRepository;
	 
//	@PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody loginDto request) {
//
//        // 1. Authenticate username & password
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        // 2. Load user details
//        UserDetails userDetails =
//                userDetailsService.loadUserByUsername(request.getEmail());
//
//        // 3. Generate JWT token
//        String token = jwtService.generateToken(userDetails);
//        
//        UserInfo user = (UserInfo) authentication.getPrincipal();
//        
//        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
//        
//        System.err.println("access token -> " + token);
//
//        // 4. Return token
//        return ResponseEntity.ok(new LoginResponse(token, 200,refreshToken.getToken()));
//    }
	 
	 @GetMapping("/test-header")
	    public void test(HttpServletResponse response) {
	        response.setHeader("X-TEST", "VISIBLE");
	    }
	 
	 
	 
	 @PostMapping("/login")
	 public ResponseEntity<Object> login(@RequestBody loginDto dto, HttpServletResponse response){
		 
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		 
		 UserInfo user =(UserInfo) authentication.getPrincipal();
		 
		 UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
		 
		 String accessToken = jwtService.generateToken(userDetails);
		 System.err.println("Access token 1 -> " + accessToken);
		 RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
		 
		 Cookie cookie = new Cookie("refreshToken", refreshToken.getToken());
		 cookie.setHttpOnly(true);
		 cookie.setSecure(false);
		 cookie.setPath("/");
		 cookie.setMaxAge(7*24*60*60);
		 
		 response.addCookie(cookie);
		 
		 response.setHeader(
			        "Set-Cookie",
			        "refreshToken=" + refreshToken.getToken() +
			        "; Path=/; HttpOnly; Max-Age=604800"
			    );
		 
		 System.err.println("refresh Token -> " + refreshToken.getToken());
		 
		 return ResponseEntity.ok(new LoginResponse(accessToken, 200, null));
		 
	 }
	
	

	    @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

	        if (userRepository.existsByEmail(request.getEmail())) {
	            return ResponseEntity
	                    .status(HttpStatus.CONFLICT)
	                    .body("Email already exists");
	        }
	        
//	        if(request.getRole().equalsIgnoreCase("Admin")) {
//	        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User with role Admin can not register. Try with customer role");
//	        }

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
	    
//	    @PostMapping("/refresh")
//	    public ResponseEntity<Object> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
//	    	
//	    	RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken()).orElseThrow(() -> new RuntimeException("Invalid Refresh Token"));
//	    	
//	    	refreshTokenService.verifyRefreshToken(refreshToken);
//	    	
//	    	UserInfo user = refreshToken.getUser();
//	    	String newAccessToken = jwtService.generateToken(user);
//	    	
//	    	System.err.println("New Access token -> " + newAccessToken);
//	    	
//	    	return ResponseEntity.ok(new LoginResponse(newAccessToken, 200, refreshToken.getToken()));
//	    	
//	    	
//	    }
	    
	    @PostMapping("/refresh")
	    public ResponseEntity<Object> refresh(HttpServletRequest request, HttpServletResponse response){
	    	
	    	String refreshTokenValue = null;
	    	
	    	Cookie[] cookies = request.getCookies();
	    	
	    	if(cookies != null) {
	    		for (Cookie cookie : cookies) {
					if("refreshToken".equalsIgnoreCase(cookie.getName())) {
						refreshTokenValue = cookie.getValue();
						break;
					}
				}
	    	}
	    	
	    	if(refreshTokenValue == null) {
	    		throw new RuntimeException("Refresh Token Missing");
	    	}
	    	
	    	RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenValue).orElseThrow(() -> new RuntimeException("Invalid Refresh Token"));
	    	
	    	refreshTokenService.verifyRefreshToken(refreshToken);
	    	
	    	
	    	UserInfo user = refreshToken.getUser();
	    	
	    	String newAccessToken = jwtService.generateToken(user);
	    	System.err.println("new Access token 2 -> " + newAccessToken);
	    	
	    	return ResponseEntity.ok(new LoginResponse(newAccessToken, 200, null));
	    }
	    
//	    @PostMapping("/logout")
//	    public ResponseEntity<Object> logout(@RequestBody RefreshTokenRequest request){
//	    	Optional<RefreshToken> tokenOpt = refreshTokenRepository.findByToken(request.getRefreshToken());
//	    	tokenOpt.ifPresent(refreshTokenService::revokeToken);
//	    	return ResponseEntity.ok("Logout successfully");
//	    }
	    
	    @PostMapping("/logout")
	    public ResponseEntity<Object> logout(
	            HttpServletRequest request,
	            HttpServletResponse response) {
	    	
	    	System.err.println("hitting logout");

	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("refreshToken".equals(cookie.getName())) {

	                    refreshTokenRepository.findByToken(cookie.getValue())
	                            .ifPresent(refreshTokenService::revokeToken);
	                    break;
	                }
	            }
	        }

	        Cookie deleteCookie = new Cookie("refreshToken", null);
	        deleteCookie.setHttpOnly(true);
	        deleteCookie.setSecure(false);
	        deleteCookie.setPath("/");
	        deleteCookie.setMaxAge(0);

	        response.addCookie(deleteCookie);

	        return ResponseEntity.ok("Logged out successfully");
	    }

}
