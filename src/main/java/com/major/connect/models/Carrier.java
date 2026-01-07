package com.major.connect.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertFalse.List;

@Entity
@Table(name = "carrier")
public class Carrier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long carrierId;
	
	@Column(name = "carrierName", unique = true)
	private String carrierName;
	private int discount_thirty_days_advance_booking;
	private int discount_sixty_days_advance_booking;
	private int discount_nintey_days_advance_booking;
	private int bulk_booking_discount;
	private int refund_for_two_days_before_travel_date;
	private int refund_for_ten_days_before_travel_date;
	private int refund_for_twenty_days_before_travel_date;
	private int silver_user_discount;
	private int gold_user_discount;
	private int platinum_user_discount;
	
	@OneToMany(
	        mappedBy = "carrier",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    private java.util.List<Flight> flights;
	
	
	public java.util.List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(java.util.List<Flight> flights) {
		this.flights = flights;
	}
	public long getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(long carrierId) {
		this.carrierId = carrierId;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public int getDiscount_percentage_thirty_days_advance_booking() {
		return discount_thirty_days_advance_booking;
	}
	public void setDiscount_percentage_thirty_days_advance_booking(int discount_percentage_thirty_days_advance_booking) {
		this.discount_thirty_days_advance_booking = discount_percentage_thirty_days_advance_booking;
	}
	public int getDiscount_percentage_sixty_days_advance_booking() {
		return discount_sixty_days_advance_booking;
	}
	public void setDiscount_percentage_sixty_days_advance_booking(int discount_percentage_sixty_days_advance_booking) {
		this.discount_sixty_days_advance_booking = discount_percentage_sixty_days_advance_booking;
	}
	public int getDiscount_percentage_nintey_days_advance_booking() {
		return discount_nintey_days_advance_booking;
	}
	public void setDiscount_percentage_nintey_days_advance_booking(int discount_percentage_nintey_days_advance_booking) {
		this.discount_nintey_days_advance_booking = discount_percentage_nintey_days_advance_booking;
	}
	public int getBulk_booking_discount() {
		return bulk_booking_discount;
	}
	public void setBulk_booking_discount(int bulk_booking_discount) {
		this.bulk_booking_discount = bulk_booking_discount;
	}
	public int getRefund_percentage_for_cancellation_two_days_before_travel_date() {
		return refund_for_two_days_before_travel_date;
	}
	public void setRefund_percentage_for_cancellation_two_days_before_travel_date(
			int refund_percentage_for_cancellation_two_days_before_travel_date) {
		this.refund_for_two_days_before_travel_date = refund_percentage_for_cancellation_two_days_before_travel_date;
	}
	public int getRefund_percentage_for_cancellation_ten_days_before_travel_date() {
		return refund_for_ten_days_before_travel_date;
	}
	public void setRefund_percentage_for_cancellation_ten_days_before_travel_date(
			int refund_percentage_for_cancellation_ten_days_before_travel_date) {
		this.refund_for_ten_days_before_travel_date = refund_percentage_for_cancellation_ten_days_before_travel_date;
	}
	public int getRefund_percentage_for_cancellation_twenty_days_before_travel_date() {
		return refund_for_twenty_days_before_travel_date;
	}
	public void setRefund_percentage_for_cancellation_twenty_days_before_travel_date(
			int refund_percentage_for_cancellation_twenty_days_before_travel_date) {
		this.refund_for_twenty_days_before_travel_date = refund_percentage_for_cancellation_twenty_days_before_travel_date;
	}
	public int getSilver_user_discount() {
		return silver_user_discount;
	}
	public void setSilver_user_discount(int silver_user_discount) {
		this.silver_user_discount = silver_user_discount;
	}
	public int getGold_user_discount() {
		return gold_user_discount;
	}
	public void setGold_user_discount(int gold_user_discount) {
		this.gold_user_discount = gold_user_discount;
	}
	public int getPlatinum_user_discount() {
		return platinum_user_discount;
	}
	public void setPlatinum_user_discount(int platinum_user_discount) {
		this.platinum_user_discount = platinum_user_discount;
	}
	public Carrier(String carrierName, int discount_percentage_thirty_days_advance_booking,
			int discount_percentage_sixty_days_advance_booking, int discount_percentage_nintey_days_advance_booking,
			int bulk_booking_discount, int refund_percentage_for_ticket_cancellation_two_days_before_travel_date,
			int refund_percentage_for_ticket_cancellation_ten_days_before_travel_date,
			int refund_percentage_for_ticket_cancellation_twenty_days_before_travel_date, int silver_user_discount,
			int gold_user_discount, int platinum_user_discount) {
		super();
		this.carrierName = carrierName;
		this.discount_thirty_days_advance_booking = discount_percentage_thirty_days_advance_booking;
		this.discount_sixty_days_advance_booking = discount_percentage_sixty_days_advance_booking;
		this.discount_nintey_days_advance_booking = discount_percentage_nintey_days_advance_booking;
		this.bulk_booking_discount = bulk_booking_discount;
		this.refund_for_two_days_before_travel_date = refund_percentage_for_ticket_cancellation_two_days_before_travel_date;
		this.refund_for_ten_days_before_travel_date = refund_percentage_for_ticket_cancellation_ten_days_before_travel_date;
		this.refund_for_twenty_days_before_travel_date = refund_percentage_for_ticket_cancellation_twenty_days_before_travel_date;
		this.silver_user_discount = silver_user_discount;
		this.gold_user_discount = gold_user_discount;
		this.platinum_user_discount = platinum_user_discount;
	}
	public Carrier() {
		
	}
	
	
	
}
