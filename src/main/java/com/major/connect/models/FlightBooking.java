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

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getSeatCategory() {
		return seatCategory;
	}

	public void setSeatCategory(String seatCategory) {
		this.seatCategory = seatCategory;
	}

	public Date getDateOfTravel() {
		return dateOfTravel;
	}

	public void setDateOfTravel(Date dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(int bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public FlightBooking(int noOfSeats, String seatCategory, Date dateOfTravel, String bookingStatus, int bookingAmount,
			Flight flight, UserInfo userInfo) {
		this.noOfSeats = noOfSeats;
		this.seatCategory = seatCategory;
		this.dateOfTravel = dateOfTravel;
		this.bookingStatus = bookingStatus;
		this.bookingAmount = bookingAmount;
		this.flight = flight;
		this.userInfo = userInfo;
	}

	public FlightBooking(long bookingId, int noOfSeats, String seatCategory, Date dateOfTravel, String bookingStatus,
			int bookingAmount, Flight flight, UserInfo userInfo) {
		this.bookingId = bookingId;
		this.noOfSeats = noOfSeats;
		this.seatCategory = seatCategory;
		this.dateOfTravel = dateOfTravel;
		this.bookingStatus = bookingStatus;
		this.bookingAmount = bookingAmount;
		this.flight = flight;
		this.userInfo = userInfo;
	}

	public FlightBooking() {
	}

	
}
