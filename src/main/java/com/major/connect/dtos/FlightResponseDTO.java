package com.major.connect.dtos;

public class FlightResponseDTO {

    private long flightId;

    // instead of Carrier entity
    private long carrierId;
    private String carrierName;

    private String origin;
    private String destination;
    private int airfare;

    private int seatCapacityBusiness;
    private int seatCapacityEconomy;
    private int seatCapacityExecutive;

    // ---------- GETTERS & SETTERS ----------

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
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
