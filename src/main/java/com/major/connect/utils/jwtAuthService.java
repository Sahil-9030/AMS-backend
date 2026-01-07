package com.major.connect.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtAuthService {

	
	private final String JWTSECRET = "ThisIsASecretKeyThisIsASecretKeyThisIsASecretKey";
	private final long VALIDITY = 24*60*60*1000;
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).after(new Date(System.currentTimeMillis() + VALIDITY));
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		return isTokenExpired(token) && extractUsername(token).equals(userDetails.getUsername());
	}
	
	public String generateToken(UserDetails userDetails) {
		
		Map<String,Object> claims = new HashMap<>();
		
		claims.put("role",userDetails.getAuthorities()
								.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()));
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.signWith(SignatureAlgorithm.HS256, generateKey())
				.setExpiration(new Date(System.currentTimeMillis() + VALIDITY))
				.compact();
	}
	
	private Key generateKey() {
		return Keys.hmacShaKeyFor(JWTSECRET.getBytes());
	}
}
