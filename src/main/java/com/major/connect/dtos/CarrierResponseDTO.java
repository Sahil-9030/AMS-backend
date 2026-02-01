package com.major.connect.dtos;
import java.util.List;

public class CarrierResponseDTO {

    private long carrierId;
    private String carrierName;
    private int discount_percentage_thirty_days_advance_booking;
    private int discount_percentage_sixty_days_advance_booking;
    private int discount_percentage_nintey_days_advance_booking;

    private int bulk_booking_discount;

    private int refund_percentage_for_cancellation_twenty_days_before_travel_date;
    private int refund_percentage_for_cancellation_ten_days_before_travel_date;
    private int refund_percentage_for_cancellation_two_days_before_travel_date;

    private int silver_user_discount;
    private int gold_user_discount;
    private int platinum_user_discount;

    // ✅ EMBEDDED FLIGHTS
    private List<FlightResponseDTO> flights;

    // -------- GETTERS & SETTERS --------

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

    public List<FlightResponseDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightResponseDTO> flights) {
        this.flights = flights;
    }

	public int getDiscount_percentage_thirty_days_advance_booking() {
		return discount_percentage_thirty_days_advance_booking;
	}

	public void setDiscount_percentage_thirty_days_advance_booking(int discount_percentage_thirty_days_advance_booking) {
		this.discount_percentage_thirty_days_advance_booking = discount_percentage_thirty_days_advance_booking;
	}

	public int getDiscount_percentage_sixty_days_advance_booking() {
		return discount_percentage_sixty_days_advance_booking;
	}

	public void setDiscount_percentage_sixty_days_advance_booking(int discount_percentage_sixty_days_advance_booking) {
		this.discount_percentage_sixty_days_advance_booking = discount_percentage_sixty_days_advance_booking;
	}

	public int getDiscount_percentage_nintey_days_advance_booking() {
		return discount_percentage_nintey_days_advance_booking;
	}

	public void setDiscount_percentage_nintey_days_advance_booking(int discount_percentage_nintey_days_advance_booking) {
		this.discount_percentage_nintey_days_advance_booking = discount_percentage_nintey_days_advance_booking;
	}

	public int getBulk_booking_discount() {
		return bulk_booking_discount;
	}

	public void setBulk_booking_discount(int bulk_booking_discount) {
		this.bulk_booking_discount = bulk_booking_discount;
	}

	public int getRefund_percentage_for_cancellation_twenty_days_before_travel_date() {
		return refund_percentage_for_cancellation_twenty_days_before_travel_date;
	}

	public void setRefund_percentage_for_cancellation_twenty_days_before_travel_date(
			int refund_percentage_for_cancellation_twenty_days_before_travel_date) {
		this.refund_percentage_for_cancellation_twenty_days_before_travel_date = refund_percentage_for_cancellation_twenty_days_before_travel_date;
	}

	public int getRefund_percentage_for_cancellation_ten_days_before_travel_date() {
		return refund_percentage_for_cancellation_ten_days_before_travel_date;
	}

	public void setRefund_percentage_for_cancellation_ten_days_before_travel_date(
			int refund_percentage_for_cancellation_ten_days_before_travel_date) {
		this.refund_percentage_for_cancellation_ten_days_before_travel_date = refund_percentage_for_cancellation_ten_days_before_travel_date;
	}

	public int getRefund_percentage_for_cancellation_two_days_before_travel_date() {
		return refund_percentage_for_cancellation_two_days_before_travel_date;
	}

	public void setRefund_percentage_for_cancellation_two_days_before_travel_date(
			int refund_percentage_for_cancellation_two_days_before_travel_date) {
		this.refund_percentage_for_cancellation_two_days_before_travel_date = refund_percentage_for_cancellation_two_days_before_travel_date;
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
    
    
}

