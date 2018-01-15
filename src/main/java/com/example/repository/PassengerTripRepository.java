package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Passenger;
import com.example.model.PassengerTrip;
import com.example.model.Wallet;

@Repository("passengerTripRepository")
public  interface PassengerTripRepository  extends JpaRepository <PassengerTrip, Long>{
	
	PassengerTrip findByPassenger(Passenger passenger);

}
