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
	protected void doFilterInternal(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        FilterChain filterChain)
	        throws ServletException, IOException {

	    String authHeader = request.getHeader("Authorization");

	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    String token = authHeader.substring(7);

	    try {
	        String username = jwtAuthService.extractUsername(token);

	        if (username != null &&
	                SecurityContextHolder.getContext().getAuthentication() == null) {

	            Claims claims = jwtAuthService.extractAllClaims(token);

	            List<String> roles = claims.get("role", List.class);

	            List<GrantedAuthority> authorities =
	                    roles.stream()
	                         .map(SimpleGrantedAuthority::new)
	                         .collect(Collectors.toList());

	            UsernamePasswordAuthenticationToken authToken =
	                    new UsernamePasswordAuthenticationToken(
	                            username,
	                            null,
	                            authorities
	                    );

	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }

	        filterChain.doFilter(request, response);

	    } catch (io.jsonwebtoken.ExpiredJwtException ex) {

	        // 🔴 THIS IS THE KEY LINE
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return;

	    } catch (Exception ex) {

	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return;
	    }
	}


}
