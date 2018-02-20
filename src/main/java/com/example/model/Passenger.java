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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "passenger")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pid")
	private int pid;
	
    @Column(name = "phoneNo",unique = true)
	@Length(min = 10,max=10, message = "*Your phone number must be 10 digit")
	@NotEmpty(message = "*Phone number can't be empty")
    private String phoneNo;

	/*@OneToMany
	private Set<PassengerTrip> passengerTrips = new HashSet <PassengerTrip>();
	
	@OneToOne
	private Wallet wallet = new Wallet();*/
	
	
	/*public Set<PassengerTrip> getPassengerTrips() {
		return passengerTrips;
	}

	public void setPassengerTrips(Set<PassengerTrip> passengerTrips) {
		this.passengerTrips = passengerTrips;
	}

	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}*/

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
