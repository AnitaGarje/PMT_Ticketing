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
@Table(name = "otherpay")
public class OtherPay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payid")
	private int payid;
	
	@Column(name = "paymode")
	private String paymode;
	
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

	public int getPayid() {
		return payid;
	}

	public void setPayid(int payid) {
		this.payid = payid;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
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
		return "OtherPay [payid=" + payid + ", paymode=" + paymode + ", username=" + username + ", password=" + password
				+ ", passenger=" + passenger + "]";
	}

	
    
    
}
