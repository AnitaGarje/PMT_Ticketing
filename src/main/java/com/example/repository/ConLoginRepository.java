package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ConLogin;
import com.example.model.ConRegister;
import com.example.model.Passenger;

@Repository("conLoginRepository")
public interface ConLoginRepository extends JpaRepository <ConLogin, Long>{
	
	ConLogin findByPhoneno(String phoneno);
}
