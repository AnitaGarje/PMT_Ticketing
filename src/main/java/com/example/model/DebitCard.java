package com.example.model;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "debitcard")

public class DebitCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "debitid")
	private int debitid;
	
	@Column(name = "cardno")
	private String cardno;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "expdate")
	private Date expdate;
	
	@Column(name = "cvv")
	private String cvv;
	
	@ManyToOne (cascade = CascadeType.ALL)
	private Passenger passenger;
	
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public int getDebitid() {
		return debitid;
	}

	public void setDebitid(int debitid) {
		this.debitid = debitid;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Date getExpdate() {
		return expdate;
	}

	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "DebitCard [debitid=" + debitid + ", cardno=" + cardno + ", expdate=" + expdate + ", cvv=" + cvv
				+ ", passenger=" + passenger + "]";
	}
	
}
