package com.major.connect.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EditFlightRequestDTO {

    @NotNull
    private Long flightId;

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @Min(0)
    private int airfare;

    @Min(0)
    private int seatCapacityBusiness;

    @Min(0)
    private int seatCapacityEconomy;

    @Min(0)
    private int seatCapacityExecutive;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
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
}
