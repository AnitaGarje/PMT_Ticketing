package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.DebitCard;
import com.example.model.Netbanking;
import com.example.model.OtherPay;
import com.example.model.Passenger;
import com.example.model.PassengerOtp;
import com.example.model.PassengerTrip;
import com.example.model.Wallet;

public interface PassengerService {
	public Passenger findPassengerByPhone(String phoneNo);
	public void savePassenger(Passenger passenger);
	//public void savePassengerOtp(PassengerOtp passengerOtp);
	public void savePassengerTrip(PassengerTrip passengerTrip);
	public void saveWallet(Wallet wallet);
	public void setPhoneToCookie(HttpServletRequest request,
	         HttpServletResponse response,String PhoneNo);
	public Passenger getPhoneFromCookie(HttpServletRequest request,
	         HttpServletResponse response);
	public Wallet getAvailBal(Passenger passenger);
	public PassengerTrip getTicketAmt(Passenger passenger);
	public Wallet getAmtTobeAdded(Passenger passenger);
	public String sendSMS(String PhoneNo);
	public double getAvalBalInWallet(HttpServletRequest request, HttpServletResponse response);
	public double getTicketCost(String from,String to,String nots);
	public List<PassengerTrip> findPassengerTripByPassenger();
	
}
