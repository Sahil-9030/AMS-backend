package com.major.connect.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.major.connect.config.UserInfoUserDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	jwtAuthService jwtAuthService;
	
	@Autowired
	UserInfoUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		System.err.println("working 2");
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			System.err.println("working 3");
			return;
		}
		
		String token = authHeader.substring(7);
		
		String username = jwtAuthService.extractUsername(token);
		
		System.err.println("Working till here "+ username);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
//			UserDetails user = userDetailsService.loadUserByUsername(username);
			
			Claims claims = jwtAuthService.extractAllClaims(token);
			
			List<String> role = claims.get("role",List.class);
			
			List<SimpleGrantedAuthority> authority = role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	
			System.err.println("Authority -> " + authority);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, authority);
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			System.err.println("AuthToken " + authToken);
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
