package com.example.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "passenger")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pid")
	private int pid;
	
	@Column(name = "phoneNo")
	private String phoneNo;

	@OneToMany(cascade = CascadeType.ALL , mappedBy = "passenger")
	private Set<PassengerTrip> passengerTrips = new HashSet <PassengerTrip>();
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "passenger")
	private Set<Wallet> wallets = new HashSet <Wallet>();
	
	
	public Set<PassengerTrip> getPassengerTrips() {
		return passengerTrips;
	}

	public void setPassengerTrips(Set<PassengerTrip> passengerTrips) {
		this.passengerTrips = passengerTrips;
	}

	public Set<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(Set<Wallet> wallets) {
		this.wallets = wallets;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
 
    
    
}
