package com.example.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "passenger")
public class Passenger2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pid")
	private int pid;
	
	@Column(name = "phoneNo")
	private String phoneNo;

	@OneToMany(cascade = CascadeType.ALL , mappedBy = "passenger")
	private Set<PassengerTrip> passengerTrips = new HashSet <PassengerTrip>();
	
	public Set<PassengerTrip> getPassengerTrips() {
		return passengerTrips;
	}

	public void setPassengerTrips(Set<PassengerTrip> passengerTrips) {
		this.passengerTrips = passengerTrips;
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
