package com.major.connect.models;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long flightId;
	
	@ManyToOne
	@JoinColumn(name = "carrierId",nullable = false)
	private Carrier carrier;
	
	private String Origin;
	private String destination;
	private int airfare;
	private int seat_capacity_business;
	private int seat_capacity_economy;
	private int seat_capacity_executive;
	
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	@Nullable
	private List<FlightBooking> bookings;

	public List<FlightBooking> getBookings() {
		return bookings;
	}
	public void setBookings(List<FlightBooking> bookings) {
		this.bookings = bookings;
	}
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getAirfare() {
		return airfare;
	}
	public void setAirfare(int airfare) {
		this.airfare = airfare;
	}
	public int getSeat_capacity_business() {
		return seat_capacity_business;
	}
	public void setSeat_capacity_business(int seat_capacity_business) {
		this.seat_capacity_business = seat_capacity_business;
	}
	public int getSeat_capacity_economy() {
		return seat_capacity_economy;
	}
	public void setSeat_capacity_economy(int seat_capacity_economy) {
		this.seat_capacity_economy = seat_capacity_economy;
	}
	public int getSeat_capacity_executive() {
		return seat_capacity_executive;
	}
	public void setSeat_capacity_executive(int seat_capacity_executive) {
		this.seat_capacity_executive = seat_capacity_executive;
	}
	public Flight(Carrier carrier, String origin, String destination, int airfare, int seat_capacity_business,
			int seat_capacity_economy, int seat_capacity_executive) {
		super();
		this.carrier = carrier;
		Origin = origin;
		this.destination = destination;
		this.airfare = airfare;
		this.seat_capacity_business = seat_capacity_business;
		this.seat_capacity_economy = seat_capacity_economy;
		this.seat_capacity_executive = seat_capacity_executive;
	}
	
	public Flight() {}
	
}
