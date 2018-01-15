package com.example.model;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "passengertrip")
public class PassengerTrip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticketid")
	private int ticketid;
	
    @Column(name = "fromloc")
	private String fromloc;
	
	@Column(name = "toloc")
	private String toloc;
	
	@Column(name = "nots")
	private String nots;
	
	@Column(name="tktamt")
	private double tktamt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dob", nullable = false,
	    columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date dob = new Date();
	
	
	@ManyToOne (cascade = CascadeType.ALL)
	private Passenger passenger;
	
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public int getTicketid() {
		return ticketid;
	}

	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}

	public String getFromloc() {
		return fromloc;
	}

	public void setFromloc(String fromloc) {
		this.fromloc = fromloc;
	}

	public String getToloc() {
		return toloc;
	}

	public void setToloc(String toloc) {
		this.toloc = toloc;
	}

	public String getNots() {
		return nots;
	}

	public void setNots(String nots) {
		this.nots = nots;
	}

	

	public double getTktamt() {
		return tktamt;
	}

	public void setTktamt(double tktamt) {
		this.tktamt = tktamt;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "PassengerTrip [ticketid=" + ticketid + ", fromloc=" + fromloc + ", toloc=" + toloc + ", nots=" + nots
				+ ", tktamt=" + tktamt + ", dob=" + dob + ", passenger=" + passenger + "]";
	}


   
		
}