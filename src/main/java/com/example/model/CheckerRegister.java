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
@Table(name = "checkerRegister")
public class CheckerRegister {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CHID")
	private int chid;
	
	@Column(name = "Name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "Bno")
	@NotEmpty(message = "*Please provide your batch no")
	private String bno;
	
	@Column(name = "PhoneNo")
	@NotEmpty(message = "*Please provide your phone no")
	private String phoneno;

	int getChid() {
		return chid;
	}

	void setChid(int chid) {
		this.chid = chid;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getBno() {
		return bno;
	}

	void setBno(String bno) {
		this.bno = bno;
	}

	String getPhoneno() {
		return phoneno;
	}

	void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	
	

}
