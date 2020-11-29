package com.hr.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.flightreservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
