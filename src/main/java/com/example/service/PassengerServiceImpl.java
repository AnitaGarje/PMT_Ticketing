package com.example.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.model.AddLocation;
import com.example.model.Bus;
import com.example.model.Location;
import com.example.model.Passenger;
import com.example.model.PassengerTrip;
import com.example.model.Wallet;
import com.example.repository.AddLocationRepository;
import com.example.repository.BusRepository;
import com.example.repository.LocationRepository;
import com.example.repository.PassengerRepository;
import com.example.repository.PassengerTripRepository;
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
	private BusRepository busRepository;
	
	

	Cookie newCookie;

	public Passenger findPassengerByPhone(String phoneNo) {
		return passengerRepository.findByPhoneNo(phoneNo);
	}
	public List<PassengerTrip> findPassengerTripByPassenger() {
		//List<PassengerTrip> ls=new ArrayList<>();
		//ls.add(passengerTripRepository.findByPassenger(passenger));
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


	public Wallet getAvailBal(Passenger passenger) {
		System.out.println("In getAvailBal Method");
		return walletRepository.findByPassenger(passenger);
	}

	public PassengerTrip getTicketAmt(Passenger passenger) {
		System.out.println("In getTicketAmt Method");
		return passengerTripRepository.findByPassenger(passenger);
	}

	public Wallet getAmtTobeAdded(Passenger passenger) {
		System.out.println("In getAmtTobeAdded Method");
		return walletRepository.findByPassenger(passenger);

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
	
	public double getAvalBalInWallet(HttpServletRequest request, HttpServletResponse response)
	{
		double avlBal;
		
		try {
			System.out.println("Pid:" + getPhoneFromCookie(request, response).getPid());
			avlBal = getAvailBal(getPhoneFromCookie(request, response)).getAvalbal();
			System.out.println("avlBal is:" + avlBal);
			

		} catch (NullPointerException e) {
			avlBal = 0.0;
			System.out.println("avlBal is:" + avlBal);

		}
		return avlBal;

	}
	
	public double getTicketCost(String from,String to,String nots)
	{
		int cost=0;
		if (from.equals("Katraj") & to.equals("balaji Nagar") || from.equals("balaji Nagar") & to.equals("Katraj"))
		{
			cost=5;
		}
		else if (from.equals("Katraj") & to.equals("balaji Nagar") || from.equals("balaji Nagar") & to.equals("Katraj"))
		{
			cost=10;
		}
		else if (from.equals("balaji Nagar") & to.equals("Swarget") || from.equals("Swarget") & to.equals("balaji Nagar"))
		{
			cost=10;
		}
		else if (from.equals("Katraj") & to.equals("Swarget") || from.equals("Swarget") & to.equals("Katraj"))
		{
			cost=10;
		}
		else if (from.equals("Katraj") & to.equals("Shivaji Nagar") || from.equals("Shivaji Nagar") & to.equals("Katraj"))
		{
			cost=15;
		}
		else if (from.equals("Swarget") & to.equals("Shivaji Nagar") || from.equals("Shivaji Nagar") & to.equals("Swarget"))
		{
			cost=10;
		}
		else if (from.equals(to))
		{
			cost=0;
			System.out.println("Please select from n to different location");
		}
		return cost*Double.parseDouble(nots);
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


}
