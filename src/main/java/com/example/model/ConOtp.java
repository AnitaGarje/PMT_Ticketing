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
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "conotp")

public class ConOtp {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OTPID")
	private int otpid;
	
	@Column(name = "CID")
	private int cid;

	@Column(name = "OTP")
	private String otp;

	
	int getOtpid() {
		return otpid;
	}

	void setOtpid(int otpid) {
		this.otpid = otpid;
	}

	int getCid() {
		return cid;
	}

	void setCid(int cid) {
		this.cid = cid;
	}

	String getOtp() {
		return otp;
	}

	void setOtp(String otp) {
		this.otp = otp;
	}
	
	

}
