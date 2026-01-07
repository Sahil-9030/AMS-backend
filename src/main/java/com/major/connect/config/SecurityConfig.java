package com.major.connect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.major.connect.utils.jwtAuthFilter;

import jakarta.security.auth.message.config.AuthConfig;

@Configuration
public class SecurityConfig{

	@Autowired
	UserInfoUserDetailsService userDetailsService;
	
	@Autowired
	jwtAuthFilter jwtAuthFilter;
	
	@Bean
	SecurityFilterChain SecurityFilterchain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(auth -> 
							auth.requestMatchers("/public/**").permitAll()
								.requestMatchers("/auth/customers/**").hasAuthority("CUSTOMER")
								.requestMatchers("/auth/admin/**").hasAuthority("ADMIN")
								.anyRequest().authenticated());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;	
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
}
