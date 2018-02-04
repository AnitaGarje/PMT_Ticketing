package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.AddLocation;
import com.example.model.Bus;
import com.example.model.FromLocation;
import com.example.model.Location;
import com.example.model.Passenger;
import com.example.model.PassengerOtp;
import com.example.model.PassengerTrip;
import com.example.model.ToLocation;
import com.example.model.Wallet;

public interface PassengerService {
	public Passenger findPassengerByPhone(String phoneNo);
	public void savePassenger(Passenger passenger);
	//public void savePassengerOtp(PassengerOtp passengerOtp);
	public void savePassengerTrip(PassengerTrip passengerTrip);
	public void saveWallet(Wallet wallet);
	public void updateWallet(Wallet wallet);
	Wallet findWalletByPassenger(Passenger passenger);
	public void setPhoneToCookie(HttpServletRequest request,
	         HttpServletResponse response,String PhoneNo);
	public Passenger getPhoneFromCookie(HttpServletRequest request,
	         HttpServletResponse response);
	public PassengerTrip getTicketAmt(Passenger passenger);
	public String sendSMS(String PhoneNo);
	public double getTicketCost(String nots,String fromloc,String toloc);
	public List<PassengerTrip> findPassengerTripByPassenger();
	public void saveRoute(AddLocation addloc);
	public void saveLocation(Location loc);
	public void saveFromLocation(FromLocation fromloc);
	public void saveToLocation(ToLocation toloc);
	public List<Location> getAllLocations();
	public void saveBus(Bus bus);
	public List<Bus> getAllBus();
	public PassengerTrip findOneTrip(int id);
	public boolean isWalletExist(Passenger passenger);
	public AddLocation findAddLocationbyfromlocAndtoloc(String fromloc,String toloc);
}
