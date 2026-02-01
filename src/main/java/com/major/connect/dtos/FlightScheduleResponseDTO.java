package com.major.connect.dtos;

import java.util.Date;

public class FlightScheduleResponseDTO {

    private Long flightScheduleId;

    private Long flightId;
    private String origin;
    private String destination;
    private String carrierName;

    private Date dateOfTravel;

    private Integer businessClassBookedCount;
    private Integer economyClassBookedCount;
    private Integer executiveClassBookedCount;

    /* ---------------- GETTERS & SETTERS ---------------- */

    public Long getFlightScheduleId() {
        return flightScheduleId;
    }

    public void setFlightScheduleId(Long flightScheduleId) {
        this.flightScheduleId = flightScheduleId;
    }

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

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Date getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public Integer getBusinessClassBookedCount() {
        return businessClassBookedCount;
    }

    public void setBusinessClassBookedCount(Integer businessClassBookedCount) {
        this.businessClassBookedCount = businessClassBookedCount;
    }

    public Integer getEconomyClassBookedCount() {
        return economyClassBookedCount;
    }

    public void setEconomyClassBookedCount(Integer economyClassBookedCount) {
        this.economyClassBookedCount = economyClassBookedCount;
    }

    public Integer getExecutiveClassBookedCount() {
        return executiveClassBookedCount;
    }

    public void setExecutiveClassBookedCount(Integer executiveClassBookedCount) {
        this.executiveClassBookedCount = executiveClassBookedCount;
    }
}
