package com.major.connect.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.major.connect.dtos.AddFlightBookingDto;
import com.major.connect.models.Flight;
import com.major.connect.models.FlightBooking;
import com.major.connect.models.UserInfo;
import com.major.connect.repositories.FlightBookingRepository;
import com.major.connect.repositories.FlightRepository;
import com.major.connect.repositories.UserRepository;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userInfoRepository;

    @Override
    public ResponseEntity<Object> addFlightBooking(AddFlightBookingDto dto) {

        try {
            Optional<Flight> flightOpt = flightRepository.findById(dto.getFlightId());
            if (flightOpt.isEmpty()) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Flight not found with ID: " + dto.getFlightId());
            }

            Optional<UserInfo> userOpt = userInfoRepository.findById(dto.getUserId());
            if (userOpt.isEmpty()) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User not found with ID: " + dto.getUserId());
            }

        Flight flight = flightOpt.get();
        UserInfo user = userOpt.get();

        FlightBooking booking = new FlightBooking();
        booking.setNoOfSeats(dto.getNoOfSeats());
        booking.setSeatCategory(dto.getSeatCategory());
        booking.setDateOfTravel(dto.getDateOfTravel());
        booking.setBookingStatus(dto.getBookingStatus());

        double baseFare = dto.getBookingAmount();

        if (dto.getSeatCategory().equalsIgnoreCase("economy")) {
            baseFare *= 1;
        } else if (dto.getSeatCategory().equalsIgnoreCase("business")) {
            baseFare *= 2;
        } else if (dto.getSeatCategory().equalsIgnoreCase("executive")) {
            baseFare *= 5;
        }

        double totalAmount = baseFare * dto.getNoOfSeats();

        double discountPercentage = 0;

        String category = user.getUser_category();

        if ("SILVER".equalsIgnoreCase(category)) {
            discountPercentage += 1;
        } else if ("GOLD".equalsIgnoreCase(category)) {
            discountPercentage += 2;
        } else if ("PLATINUM".equalsIgnoreCase(category)) {
            discountPercentage += 4;
        }

        Date today = new Date();
        Date travelDate = dto.getDateOfTravel();

        LocalDate todayLocal = today.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        LocalDate travelLocal = travelDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(todayLocal, travelLocal);


        if (daysBetween >= 90) {
            discountPercentage += 5;
        } else if (daysBetween >= 60) {
            discountPercentage += 3;
        } else if (daysBetween >= 30) {
            discountPercentage += 2;
        }

        if (dto.getNoOfSeats() >= 10) {
            discountPercentage += 2;
        }

        double discountAmount = (totalAmount * discountPercentage) / 100;
        double finalAmount = totalAmount - discountAmount;

        booking.setBookingAmount((int) Math.round(finalAmount));
        booking.setFlight(flight);
        booking.setUserInfo(user);

        FlightBooking savedBooking = flightBookingRepository.save(booking);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBooking);

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error while booking flight: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> cancelMyFlightBooking(long bookingId) {

    try {
        Optional<FlightBooking> bookingOpt = flightBookingRepository.findById(bookingId);

        if (bookingOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Booking not found with ID: " + bookingId);
        }

        FlightBooking booking = bookingOpt.get();

        
        if ("CANCELLED".equalsIgnoreCase(booking.getBookingStatus())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Booking is already cancelled");
        }

        
        Date today = new Date();
        Date travelDate = booking.getDateOfTravel();

        LocalDate todayLocal = today.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate travelLocal = travelDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long daysBeforeTravel = ChronoUnit.DAYS.between(todayLocal, travelLocal);

        double refundPercentage = 0;

        if (daysBeforeTravel >= 20) {
            refundPercentage = 95;
        } else if (daysBeforeTravel >= 10) {
            refundPercentage = 70;
        } else if (daysBeforeTravel >= 2) {
            refundPercentage = 40;
        }

        int totalPaidAmount = booking.getBookingAmount();
        double refundAmount = (totalPaidAmount * refundPercentage) / 100;

        booking.setBookingStatus("CANCELLED");
        flightBookingRepository.save(booking);

        return ResponseEntity.ok(
                "Booking cancelled successfully. Refund Amount: ₹" + Math.round(refundAmount)
        );

    } catch (Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error while cancelling booking: " + e.getMessage());
    }
    }

   

    
}
