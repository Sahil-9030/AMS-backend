package com.major.connect.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long flightScheduleId;
	
	@OneToOne
	@JoinColumn(name = "flightId", nullable = false)
	private Flight flight;
	
	private Date dateOfTravel;
	private int businessClassBookedCount;
	private int economyClassBookedCount;
	private int ExecutiveClassBookedCount;
	
	
	

}
