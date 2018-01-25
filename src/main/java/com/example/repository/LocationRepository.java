package com.example.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AddLocation;
import com.example.model.Location;
import com.example.model.Passenger;
import com.example.model.PassengerTrip;
import com.example.model.Wallet;

@Repository("locationRepository")
public  interface LocationRepository  extends JpaRepository <Location, Long>{
	//List<Location> findAll();
	
	

}
