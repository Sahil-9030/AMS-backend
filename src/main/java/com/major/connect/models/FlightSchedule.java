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

	public long getFlightScheduleId() {
		return flightScheduleId;
	}
	public void setFlightScheduleId(long flightScheduleId) {
		this.flightScheduleId = flightScheduleId;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Date getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(Date dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
	public int getBusinessClassBookedCount() {
		return businessClassBookedCount;
	}
	public void setBusinessClassBookedCount(int businessClassBookedCount) {
		this.businessClassBookedCount = businessClassBookedCount;
	}
	public int getEconomyClassBookedCount() {
		return economyClassBookedCount;
	}
	public void setEconomyClassBookedCount(int economyClassBookedCount) {
		this.economyClassBookedCount = economyClassBookedCount;
	}
	public int getExecutiveClassBookedCount() {
		return ExecutiveClassBookedCount;
	}
	public void setExecutiveClassBookedCount(int executiveClassBookedCount) {
		ExecutiveClassBookedCount = executiveClassBookedCount;
	}
	public FlightSchedule(long flightScheduleId, Flight flight, Date dateOfTravel, int businessClassBookedCount,
			int economyClassBookedCount, int executiveClassBookedCount) {
		this.flightScheduleId = flightScheduleId;
		this.flight = flight;
		this.dateOfTravel = dateOfTravel;
		this.businessClassBookedCount = businessClassBookedCount;
		this.economyClassBookedCount = economyClassBookedCount;
		ExecutiveClassBookedCount = executiveClassBookedCount;
	}
	public FlightSchedule(Flight flight, Date dateOfTravel, int businessClassBookedCount, int economyClassBookedCount,
			int executiveClassBookedCount) {
		this.flight = flight;
		this.dateOfTravel = dateOfTravel;
		this.businessClassBookedCount = businessClassBookedCount;
		this.economyClassBookedCount = economyClassBookedCount;
		ExecutiveClassBookedCount = executiveClassBookedCount;
	}
	public FlightSchedule() {
	}
	
}
