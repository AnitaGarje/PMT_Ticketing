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
@Table(name = "AdminDetail")
public class AdminDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Tripid")
	private int Tripid;
	
	@Column(name = "AID")
	private int aid;
	
	@Column(name = "Bno")
	private String bno;
	
	@Column(name = "Date")
	private String date;
	
	@Column(name = "Time")
	private String Time;

	int getTripid() {
		return Tripid;
	}

	void setTripid(int tripid) {
		Tripid = tripid;
	}

	int getAid() {
		return aid;
	}

	void setAid(int aid) {
		this.aid = aid;
	}

	String getBno() {
		return bno;
	}

	void setBno(String bno) {
		this.bno = bno;
	}

	String getDate() {
		return date;
	}

	void setDate(String date) {
		this.date = date;
	}

	String getTime() {
		return Time;
	}

	void setTime(String time) {
		Time = time;
	}
	
	

}
