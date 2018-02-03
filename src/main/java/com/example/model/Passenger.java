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
	@Pattern(regexp="(^$|[0-9]{10})",message = "*Please provide a valid mobile no")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (pid != other.pid)
			return false;
		return true;
	}
	
 
    
    
}
