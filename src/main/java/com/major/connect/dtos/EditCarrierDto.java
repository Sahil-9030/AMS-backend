package com.major.connect.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EditCarrierDto {

    @NotBlank
    private long carrierId;

    @NotBlank
    private String carrierName;

    @Min(0) @Max(100)
    @JsonProperty("discount_percentage_thirty_days_advance_booking")
    private int discountPercentageThirtyDaysAdvanceBooking;

    @Min(0) @Max(100)
    @JsonProperty("discount_percentage_sixty_days_advance_booking")
    private int discountPercentageSixtyDaysAdvanceBooking;

    @Min(0) @Max(100)
    @JsonProperty("discount_percentage_ninety_days_advance_booking")
    private int discountPercentageNinetyDaysAdvanceBooking;

    @Min(0) @Max(100)
    @JsonProperty("bulk_booking_discount")
    private int bulkBookingDiscount;

    @Min(0) @Max(100)
    @JsonProperty("refund_percentage_for_cancellation_two_days_before_travel_date")
    private int refundPercentageTwoDaysBeforeTravelDate;

    @Min(0) @Max(100)
    @JsonProperty("refund_percentage_for_cancellation_ten_days_before_travel_date")
    private int refundPercentageTenDaysBeforeTravelDate;

    @Min(0) @Max(100)
    @JsonProperty("refund_percentage_for_cancellation_twenty_days_before_travel_date")
    private int refundPercentageTwentyDaysBeforeTravelDate;

    @Min(0) @Max(100)
    @JsonProperty("silver_user_discount")
    private int silverUserDiscount;

    @Min(0) @Max(100)
    @JsonProperty("gold_user_discount")
    private int goldUserDiscount;

    @Min(0) @Max(100)
    @JsonProperty("platinum_user_discount")
    private int platinumUserDiscount;

    // ---------- Getters & Setters ----------

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

    public int getDiscountPercentageThirtyDaysAdvanceBooking() {
        return discountPercentageThirtyDaysAdvanceBooking;
    }

    public void setDiscountPercentageThirtyDaysAdvanceBooking(int discountPercentageThirtyDaysAdvanceBooking) {
        this.discountPercentageThirtyDaysAdvanceBooking = discountPercentageThirtyDaysAdvanceBooking;
    }

    public int getDiscountPercentageSixtyDaysAdvanceBooking() {
        return discountPercentageSixtyDaysAdvanceBooking;
    }

    public void setDiscountPercentageSixtyDaysAdvanceBooking(int discountPercentageSixtyDaysAdvanceBooking) {
        this.discountPercentageSixtyDaysAdvanceBooking = discountPercentageSixtyDaysAdvanceBooking;
    }

    public int getDiscountPercentageNinetyDaysAdvanceBooking() {
        return discountPercentageNinetyDaysAdvanceBooking;
    }

    public void setDiscountPercentageNinetyDaysAdvanceBooking(int discountPercentageNinetyDaysAdvanceBooking) {
        this.discountPercentageNinetyDaysAdvanceBooking = discountPercentageNinetyDaysAdvanceBooking;
    }

    public int getBulkBookingDiscount() {
        return bulkBookingDiscount;
    }

    public void setBulkBookingDiscount(int bulkBookingDiscount) {
        this.bulkBookingDiscount = bulkBookingDiscount;
    }

    public int getRefundPercentageTwoDaysBeforeTravelDate() {
        return refundPercentageTwoDaysBeforeTravelDate;
    }

    public void setRefundPercentageTwoDaysBeforeTravelDate(int refundPercentageTwoDaysBeforeTravelDate) {
        this.refundPercentageTwoDaysBeforeTravelDate = refundPercentageTwoDaysBeforeTravelDate;
    }

    public int getRefundPercentageTenDaysBeforeTravelDate() {
        return refundPercentageTenDaysBeforeTravelDate;
    }

    public void setRefundPercentageTenDaysBeforeTravelDate(int refundPercentageTenDaysBeforeTravelDate) {
        this.refundPercentageTenDaysBeforeTravelDate = refundPercentageTenDaysBeforeTravelDate;
    }

    public int getRefundPercentageTwentyDaysBeforeTravelDate() {
        return refundPercentageTwentyDaysBeforeTravelDate;
    }

    public void setRefundPercentageTwentyDaysBeforeTravelDate(int refundPercentageTwentyDaysBeforeTravelDate) {
        this.refundPercentageTwentyDaysBeforeTravelDate = refundPercentageTwentyDaysBeforeTravelDate;
    }

    public int getSilverUserDiscount() {
        return silverUserDiscount;
    }

    public void setSilverUserDiscount(int silverUserDiscount) {
        this.silverUserDiscount = silverUserDiscount;
    }

    public int getGoldUserDiscount() {
        return goldUserDiscount;
    }

    public void setGoldUserDiscount(int goldUserDiscount) {
        this.goldUserDiscount = goldUserDiscount;
    }

    public int getPlatinumUserDiscount() {
        return platinumUserDiscount;
    }

    public void setPlatinumUserDiscount(int platinumUserDiscount) {
        this.platinumUserDiscount = platinumUserDiscount;
    }
}

