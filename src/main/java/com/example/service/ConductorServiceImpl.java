package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.model.ConLogin;
import com.example.model.ConRegister;
import com.example.model.OtherPay;
import com.example.model.Passenger;
import com.example.repository.ConLoginRepository;
import com.example.repository.ConRegisterRepository;
import com.example.repository.OtherPayRepository;
import com.example.repository.PassengerRepository;

@Service
@Qualifier("conductorService")
public class ConductorServiceImpl implements ConductorService{


	@Autowired
	private ConRegisterRepository conRegisterRepository;
	

	
	@Override
	public ConRegister findConRegisterByPhoneno(String phoneno) {
		return conRegisterRepository.findByPhoneno(phoneno);
	}

	public void saveConRegister(ConRegister  conRegister) {
		System.out.println("In saveOtherPay Method");
		conRegister.setCid(conRegister.getCid());
		conRegister.setName(conRegister.getName());
		conRegister.setBno(conRegister.getBno());
		conRegister.setPhoneno(conRegister.getPhoneno());
		conRegisterRepository.save(conRegister);
	}
	
	@Autowired
	private ConLoginRepository conLoginRepository;
	
	public ConLogin findConLoginByPhoneno(String phoneno) {
		return conLoginRepository.findByPhoneno(phoneno);
	}

	public void saveConLogin(ConLogin  conLogin) {
		System.out.println("In saveConLogin Method");
		conLogin.setLoginid(conLogin.getLoginid());
		//conLogin.setCid(conLogin.getCid());
		conLogin.setPhoneno(conLogin.getPhoneno());
		conLoginRepository.save(conLogin);
	}
}
