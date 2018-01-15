package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ConRegister;
import com.example.model.Passenger;

@Repository("conRegisterRepository")
public interface ConRegisterRepository extends JpaRepository <ConRegister, Long>{
	
	ConRegister findByPhoneno(String phoneno);

}







