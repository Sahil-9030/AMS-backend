package com.major.connect.dtos;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 78140cefe7efad44b6a9814f0898869b087fa4af
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddFlightDto {

    @NotNull(message = "Carrier ID is required")
    private Long carrierId;

    @NotBlank(message = "Origin is required")
    private String origin;

    @NotBlank(message = "Destination is required")
    private String destination;

    @Min(value = 0, message = "Airfare must be >= 0")
    private int airfare;

    @Min(value = 0, message = "Business seat capacity must be >= 0")
    private int seatCapacityBusiness;

    @Min(value = 0, message = "Economy seat capacity must be >= 0")
    private int seatCapacityEconomy;

    @Min(value = 0, message = "Executive seat capacity must be >= 0")
    private int seatCapacityExecutive;

<<<<<<< HEAD
	 private Long scheduleId;
    private List<Long> bookingIds;

	 public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<Long> getBookingIds() {
        return bookingIds;
    }

    public void setBookingIds(List<Long> bookingIds) {
        this.bookingIds = bookingIds;
    }

=======
>>>>>>> 78140cefe7efad44b6a9814f0898869b087fa4af
	public Long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
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

	public int getSeatCapacityBusiness() {
		return seatCapacityBusiness;
	}

	public void setSeatCapacityBusiness(int seatCapacityBusiness) {
		this.seatCapacityBusiness = seatCapacityBusiness;
	}

	public int getSeatCapacityEconomy() {
		return seatCapacityEconomy;
	}

	public void setSeatCapacityEconomy(int seatCapacityEconomy) {
		this.seatCapacityEconomy = seatCapacityEconomy;
	}

	public int getSeatCapacityExecutive() {
		return seatCapacityExecutive;
	}

	public void setSeatCapacityExecutive(int seatCapacityExecutive) {
		this.seatCapacityExecutive = seatCapacityExecutive;
	}
    
    

    // getters and setters
}

