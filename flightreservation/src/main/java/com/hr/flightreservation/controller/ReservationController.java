package com.hr.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hr.flightreservation.dto.ReservationRequest;
import com.hr.flightreservation.entity.Flight;
import com.hr.flightreservation.entity.Reservation;
import com.hr.flightreservation.repos.FlightRepository;
import com.hr.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Flight flight=flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelmap) {
		
		Reservation reservation = reservationService.bookFlight(request);
		modelmap.addAttribute("msg", "Reservation created successfully with id: "+reservation.getId());
		return "reservationConfirmation";
	}
}
