package com.major.connect.services;

<<<<<<< HEAD
import java.util.Date;

=======
>>>>>>> 78140cefe7efad44b6a9814f0898869b087fa4af
import org.springframework.http.ResponseEntity;

import com.major.connect.dtos.AddFlightDto;

public interface FlightService {
	ResponseEntity<Object>addFlight(AddFlightDto dto);
<<<<<<< HEAD
	ResponseEntity<Object> getFlightById(long flightId);
	ResponseEntity<Object> getAllFlights();
	ResponseEntity<Object> editFlightById(long flightId);
	ResponseEntity<Object> deleteFlightById(long flightId);

	ResponseEntity<Object> getFlightForSpecificRouteAndDate(String origin, String destination, Date travelDate);
=======
>>>>>>> 78140cefe7efad44b6a9814f0898869b087fa4af
}
