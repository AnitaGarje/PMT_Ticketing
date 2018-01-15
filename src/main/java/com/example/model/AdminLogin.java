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
@Table(name = "AdminLogin")
public class AdminLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Loginid")
	private int loginid;
	
	@Column(name = "AID")
	private int aid;

	@Column(name = "PhoneNo")
	private String phoneNo;

	int getLoginid() {
		return loginid;
	}

	void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	int getAid() {
		return aid;
	}

	void setAid(int aid) {
		this.aid = aid;
	}

	String getPhoneNo() {
		return phoneNo;
	}

	void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
