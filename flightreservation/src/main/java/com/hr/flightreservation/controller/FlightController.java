package com.hr.flightreservation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hr.flightreservation.entity.Flight;
import com.hr.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	private FlightRepository flightRepository;
	
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from,@RequestParam("to") String to, ModelMap modelMap) {
		List<Flight> flights= flightRepository.findFlights(from,to);
		modelMap.addAttribute("flights", flights);
		return "displayFlights";
		
	}
	
	//<11. startes>
	//this URI is only for those who have admin role
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}
	//<11. ends>
}
