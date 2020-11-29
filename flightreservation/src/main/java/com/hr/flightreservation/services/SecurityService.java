package com.hr.flightreservation.services;

//<11>
public interface SecurityService {
	boolean login(String username, String password);
}
