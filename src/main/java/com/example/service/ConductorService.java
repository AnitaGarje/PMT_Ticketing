package com.example.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.ConLogin;
import com.example.model.ConRegister;
import com.example.model.Passenger;

public interface ConductorService {
	public ConRegister findConRegisterByPhoneno(String phoneno);
	public void saveConRegister(ConRegister conRegister);
	public ConLogin findConLoginByPhoneno(String phoneno);
	public void saveConLogin(ConLogin conLogin);
	
	
	

}
