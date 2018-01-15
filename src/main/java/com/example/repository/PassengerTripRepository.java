package com.example.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Passenger;
import com.example.model.PassengerTrip;
import com.example.model.Wallet;

@Repository("passengerTripRepository")
public  interface PassengerTripRepository  extends JpaRepository <PassengerTrip, Long>{
	
	//List<PassengerTrip> findAll(Passenger passenger);
	PassengerTrip findByPassenger(Passenger passenger);
	List<PassengerTrip> findAll();
	//List<PassengerTrip> findAll(Passenger passenger);

}
