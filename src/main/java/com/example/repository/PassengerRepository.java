package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.example.model.Passenger;
import com.example.model.PassengerOtp;


@Repository("passengerRepository")
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	Passenger findByPhoneNo(String phoneNo);
	
	}


