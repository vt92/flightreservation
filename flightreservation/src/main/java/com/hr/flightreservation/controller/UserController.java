package com.hr.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hr.flightreservation.entity.User;
import com.hr.flightreservation.repos.UserRepository;
import com.hr.flightreservation.services.SecurityService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	//<11.>
	@Autowired
	private SecurityService securityService;
	
	//<9.>
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired //<11.>
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		return "login/registerUser";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		//<11. starts>
		user.setPassword(encoder.encode(user.getPassword()));
		//<11. ends>
		userRepository.save(user);
		return "login/login";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		LOGGER.error("ERROR");
		LOGGER.warn("WARN");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.trace("TRACE");
		
		LOGGER.info("Email:"+email);
		
		//<old step to check username password starts
	//	User user = userRepository.findByEmail(email);
	//	if(user.getPassword().equals(password)) {
	//		return "findFlights";
	//	}
	//	else {
	//		modelMap.addAttribute("msg", "incorrect username password");
	//	}
		//<old step to check username password ends
		
		//<11. starts>
		boolean loginResponse = securityService.login(email, password);
		LOGGER.info("LoginResponse "+loginResponse);
		if(loginResponse) {
			return "findFlights";
		}else {
			modelMap.addAttribute("msg", "incorrect username password");
		}
		//<11. ends>
		
		return "login/login";
	}
	
	
}
