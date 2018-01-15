package com.example.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.ConLogin;
import com.example.model.ConRegister;
import com.example.model.DebitCard;
import com.example.model.Netbanking;
import com.example.model.OtherPay;
import com.example.model.Passenger;
import com.example.model.PassengerOtp;
import com.example.model.PassengerTrip;
import com.example.model.User;
import com.example.model.Wallet;
import com.example.service.ConductorService;
import com.example.service.PassengerService;
import com.example.service.UserService;

@Controller
public class ConductorController {
	
	@Autowired
	private ConductorService conductorService;
	
	@RequestMapping(value="/conRegister", method = RequestMethod.GET)
	public ModelAndView conRegister(){
		ModelAndView modelAndView = new ModelAndView();
		ConRegister conRegister = new ConRegister();
		modelAndView.addObject("conRegister", conRegister);
		modelAndView.setViewName("conRegister");
		return modelAndView;
	}
	
	@RequestMapping(value = "/conRegister", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid ConRegister conRegister,BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		ConRegister conRegisterExists = conductorService.findConRegisterByPhoneno(conRegister.getPhoneno());
		if (conRegisterExists != null) {
			bindingResult
					.rejectValue("phoneno", "error.conRegister",
							"There is already a conductor registered with the phoneno provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("conRegister");
		} else {
			conductorService.saveConRegister(conRegister);
			modelAndView.addObject("successMessage", "Conductor has been registered successfully");
			modelAndView.addObject("conRegister", new ConRegister());
			modelAndView.setViewName("conRegister");
			
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/conLogin", method = RequestMethod.GET)
	public ModelAndView conLogin(){
		ModelAndView modelAndView = new ModelAndView();
		ConLogin conLogin = new ConLogin();
		modelAndView.addObject("conLogin", conLogin);
		modelAndView.setViewName("conLogin");
		return modelAndView;
	}
	
	@RequestMapping(value = "/conLogin", method = RequestMethod.POST)
	public ModelAndView createNewconLogin(@Valid ConLogin conLogin, BindingResult bindingResult) {
		System.out.println("In createConLogin Method" );
	    ModelAndView modelAndView = new ModelAndView();
	  conductorService.saveConLogin(conLogin);
		modelAndView.addObject("conLogin", new ConLogin());
		modelAndView.setViewName("conLogin");	
	
		//}
		return modelAndView;
	}
	
	

}
