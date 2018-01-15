package com.example.model;
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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "netbanking")
public class Netbanking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Netid")
	private int netid;
	
	@Column(name = "bankname")
	private String bankname;
	
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne (cascade = CascadeType.ALL)
	private Passenger passenger;
	
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}


	public int getNetid() {
		return netid;
	}

	public void setNetid(int netid) {
		this.netid = netid;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Netbanking [netid=" + netid + ", bankname=" + bankname + ", username=" + username + ", password="
				+ password + ", passenger=" + passenger + "]";
	}

	
	
	

}
