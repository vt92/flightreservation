package com.hr.flightreservation.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hr.flightreservation.dto.ReservationRequest;
import com.hr.flightreservation.entity.Flight;
import com.hr.flightreservation.entity.Passenger;
import com.hr.flightreservation.entity.Reservation;
import com.hr.flightreservation.repos.FlightRepository;
import com.hr.flightreservation.repos.PassengerRepository;
import com.hr.flightreservation.repos.ReservationRepository;
import com.hr.flightreservation.util.EmailUtil;
import com.hr.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;
	
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	PDFGenerator pdfGenerator;
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		//Make Payment
		System.out.println(request.getFlightId());
		System.out.println(request.getNameOnCard());
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		//generating pdf code
		//dynamically appending the file name
		String filePath =ITINERARY_DIR +savedReservation.getId()+".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		//email sending code
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
