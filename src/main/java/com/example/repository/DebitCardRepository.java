package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.DebitCard;
import com.example.model.Passenger;
import com.example.model.PassengerTrip;

@Repository("debitCardRepository")
public interface DebitCardRepository extends JpaRepository < DebitCard, Long>{
	

}



