package com.hr.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.flightreservation.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
