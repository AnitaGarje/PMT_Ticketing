package com.example.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.model.AddLocation;
import com.example.model.Bus;
import com.example.model.FromLocation;
import com.example.model.Location;
import com.example.model.Passenger;
import com.example.model.PassengerTrip;
import com.example.model.ToLocation;
import com.example.model.Wallet;
import com.example.repository.AddLocationRepository;
import com.example.repository.BusRepository;
import com.example.repository.FromLocationRepository;
import com.example.repository.LocationRepository;
import com.example.repository.PassengerRepository;
import com.example.repository.PassengerTripRepository;
import com.example.repository.ToLocationRepository;
import com.example.repository.WalletRepository;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

@Service
@Qualifier("passengerService")
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private PassengerTripRepository passengerTripRepository;
	
	@Autowired
	private AddLocationRepository addLocationRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private FromLocationRepository fromlocationRepository;
	
	@Autowired
	private ToLocationRepository tolocationRepository;
	
	@Autowired
	private BusRepository busRepository;
	

	

	Cookie newCookie;
	Cookie walletCookie;

	public Passenger findPassengerByPhone(String phoneNo) {
		return passengerRepository.findByPhoneNo(phoneNo);
	}
	public List<PassengerTrip> findPassengerTripByPassenger() {
		return passengerTripRepository.findAll();
	}

	public void savePassenger(Passenger passenger) {
		System.out.println("In PassengerServiceImpl save method using CRUD");
		passengerRepository.save(passenger);
	}

	public void savePassengerTrip(PassengerTrip passengerTrip) {
		passengerTripRepository.save(passengerTrip);
	}

	public void saveWallet(Wallet wallet) {
		walletRepository.save(wallet);
	}


	public PassengerTrip getTicketAmt(Passenger passenger) {
		System.out.println("In getTicketAmt Method");
		return passengerTripRepository.findByPassenger(passenger);
	}

	

	public void setPhoneToCookie(HttpServletRequest request, HttpServletResponse response, String PhoneNo) {
		System.out.println("Before creating Cookies :");
		// Add Phone number to cookie
		newCookie = new Cookie("CookiePhoneNo", PhoneNo);
		newCookie.setMaxAge(24 * 60 * 60);
		System.out.println("After creating Cookies :");
		response.addCookie(newCookie);
		System.out.println("After adding to Cookies :");
	}

	public Passenger getPhoneFromCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("CookiePhoneNo")) {

					return findPassengerByPhone(cookie.getValue());
				}
			}
		}

		return null;
	}
	
	public void setAmountToCookie(HttpServletRequest request, HttpServletResponse response, String Amt) {
		System.out.println("Before creating Cookies :");
		// Add Phone number to cookie
		walletCookie = new Cookie("CookieWalletAmt", Amt);
		walletCookie.setMaxAge(24 * 60 * 60);
		System.out.println("After creating Cookies :");
		response.addCookie(walletCookie);
		System.out.println("After adding to Cookies :");
	}
	
	public String getWalletAmtCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("CookieWalletAmt")) {

					return cookie.getValue();
				}
			}
		}

		return null;
	}


	public String sendSMS(String PhoneNo) {
		String OTP = "NOOTP";
		try {
		/* String host = "smtp.gmail.com"; 
		  String user ="abusabu31@gmail.com"; 
		  String pss = "abusabu@31"; 
		  String to ="91"+PhoneNo+"@txtlocal.co.uk"; 
		  String from = user; 
		  String subject = "Message";*/
		 
		  Random random = new Random();
		  OTP = String.format("%04d", random.nextInt(10000));
		  String message = "Your OTP for Mobile Verification is : " + OTP;
		  System.out.println("message :" + message + " PhoneNo is  :" + PhoneNo);
		
		/* boolean sessionDebug = false; 
		  Properties pros =System.getProperties();
		  pros.put("mail.smtp.starttls.enable", "tue");
		  pros.put("mail.smtp.host", "host");
		  pros.put("mail.smtp.auth","true");
		  pros.put("mail.smtp.port","587");
		  pros.put("mail.smtp.starttls.required","true");
		  java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider()); 
		  Session mailSession = Session.getDefaultInstance(pros,null);
		  mailSession.setDebug(sessionDebug);
		  Message msg = new MimeMessage(mailSession);
		  msg.setFrom(new InternetAddress(from));
		  InternetAddress [] address ={new InternetAddress(to)};
		  msg.setRecipients(Message.RecipientType.TO, address);
		  msg.setSubject(subject); msg.setSentDate(new Date());
		  msg.setText(message); 
		  Transport transport = mailSession.getTransport("smtp"); 
		  transport.connect(host, user, pss); 
		  transport.sendMessage(msg, msg.getAllRecipients());
		  transport.close();*/
		  System.out.println("message sent successfully");

		} catch (Exception ex) {
			// JOptionPane.showMessageDialog(null, ex);
			System.out.println("Exception is:" + ex);
		}
		return OTP;

	}
	
	
	public double getTicketCost(String nots,String fromloc,String toloc)
	{
	
		int fare=2;
		int distance=0;
		if(fromloc.equals(toloc))
		{
			distance=0;
		}
		else
		{
			try{
				distance=Integer.parseInt(findAddLocationbyfromlocAndtoloc(fromloc,toloc).getDistance());
			}
			catch(Exception e)
			{
				System.out.println("Exception is:"+e.getMessage());
				try{
				distance=Integer.parseInt(findAddLocationbyfromlocAndtoloc(toloc,fromloc).getDistance());
				}
				catch(Exception e1)
				{
					System.out.println("Please add route to get distance");
					distance=-1;
				}
			}
		}
		
		return Double.parseDouble(nots)*fare*distance;
	}
	@Override
	public void saveRoute(AddLocation addloc) {
		
		
		System.out.println("In saveRoute save method using CRUD");
		addLocationRepository.save(addloc);
	}
	@Override
	public void saveLocation(Location loc) {
		// TODO Auto-generated method stub
		System.out.println("In saveLocation save method using CRUD");
		locationRepository.save(loc);
		
	}
	@Override
	public List<Location> getAllLocations() {
		// TODO Auto-generated method stub
		return locationRepository.findAll();
	}
	@Override
	public void saveBus(Bus bus) {
		System.out.println("In saveBus save method using CRUD");
		busRepository.save(bus);
		
	}
	@Override
	public List<Bus> getAllBus() {
		// TODO Auto-generated method stub
		return busRepository.findAll();
	}
	@Override
	public PassengerTrip findOneTrip(int id) {
		// TODO Auto-generated method stub
		return passengerTripRepository.findOne(id);
	}
	@Override
	public void updateWallet(Wallet wallet) {
		 walletRepository.save(wallet);
		 //walletRepository.
		
	}
	@Override
	public Wallet findWalletByPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		return walletRepository.findByPassenger(passenger);
	}
	
	@Override
	public boolean isWalletExist(Passenger passenger ) {
		System.out.println("Inside isWalletExist");
		 try{
			
			 int avlBal=(int) findWalletByPassenger(passenger).getAvalbal();
		 }
		 catch(NullPointerException e){
			 System.out.println("Exception in isWalletExist"+e.getMessage());
			 return false;
		 }
		return true;
	}
	@Override
	public AddLocation findAddLocationbyfromlocAndtoloc(String fromloc,String toloc) {
		// TODO Auto-generated method stub
		return addLocationRepository.findByFromlocAndToloc(fromloc, toloc);
	}
	@Override
	public void saveFromLocation(FromLocation fromloc) {
		// TODO Auto-generated method stub
		fromlocationRepository.save(fromloc);
		
	}
	@Override
	public void saveToLocation(ToLocation toloc) {
		tolocationRepository.save(toloc);
		
	}
	


}
