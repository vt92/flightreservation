package com.hr.flightreservation.services;

import com.hr.flightreservation.dto.ReservationRequest;
import com.hr.flightreservation.entity.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request); 
}
