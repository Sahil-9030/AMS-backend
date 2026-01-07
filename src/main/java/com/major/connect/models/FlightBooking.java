package com.major.connect.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	
	private int noOfSeats;
	
	private String seatCategory;
	
	private Date dateOfTravel;
	
	private String bookingStatus;
	
	private int bookingAmount;
	
	@ManyToOne
	@JoinColumn(name = "flightId", nullable = false)
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userInfo;
}
