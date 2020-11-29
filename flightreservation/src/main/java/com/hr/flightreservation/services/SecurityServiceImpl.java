package com.hr.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hr.flightreservation.controller.UserController;

//<11>
@Service
public class SecurityServiceImpl implements SecurityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public boolean login(String username, String password) {
		LOGGER.info("username:" +username);
		LOGGER.info("password:" +password);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		LOGGER.info("userDetails:" +userDetails.toString());
		
		//username and password authentication token,
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
		authenticationManager.authenticate(token);
		//to get the value of authentication done by aithentication manager
		boolean result = token.isAuthenticated();
		//last step is to set token into the spring security context holder
		//meaning spring will store the detials and wont ask everytime to pass the credentials
		if(result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		
		return result;
	}

}
