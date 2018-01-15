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
@Table(name = "AdminOtp")
public class AdminOtp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OTPID")
	private int otpid;
	
	@Column(name = "AID")
	private int aid;

	@Column(name = "OTP")
	private String otp;

	int getOtpid() {
		return otpid;
	}

	void setOtpid(int otpid) {
		this.otpid = otpid;
	}

	int getAid() {
		return aid;
	}

	void setAid(int aid) {
		this.aid = aid;
	}

	String getOtp() {
		return otp;
	}

	void setOtp(String otp) {
		this.otp = otp;
	}

	

}
