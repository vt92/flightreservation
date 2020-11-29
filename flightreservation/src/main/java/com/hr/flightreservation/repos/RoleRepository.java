package com.hr.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.flightreservation.entity.Role;
//<11>
public interface RoleRepository extends JpaRepository<Role, Long> {

}
