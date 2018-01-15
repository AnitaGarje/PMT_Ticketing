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
@Table(name = "CheckerLogin")

public class CheckerLogin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Loginid")
	private int loginid;
	
	@Column(name = "CHID")
	private int chid;

	@Column(name = "PhoneNo")
	private String phoneNo;

	int getLoginid() {
		return loginid;
	}

	void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	int getChid() {
		return chid;
	}

	void setChid(int chid) {
		this.chid = chid;
	}

	String getPhoneNo() {
		return phoneNo;
	}

	void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
}
