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
@Table(name = "contrip")

public class ConTrip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Tripid")
	private int Tripid;
	
	/*@ManyToOne (cascade = CascadeType.ALL)
	private ConRegister conRegister;
	
	public ConRegister getConRegister() {
		return conRegister;
	}

	public void setConRegister(ConRegister conRegister) {
		this.conRegister = conRegister;
	}*/
	
	

	@Column(name = "Bno")
	private String bno;
	
	@Column(name = "DepoName")
	@NotEmpty(message = "*Please provide your depot name")
	private String deponame;
	
	@Column(name = "Num")
	@NotEmpty(message = "*Please provide your number")
	private int num;
	 
	@Column(name = "Tripno")
	@NotEmpty(message = "*Please provide your trip no")
	private int tripno;
	
	@Column(name = "Date")
	private String date;
	
	@Column(name = "Time")
	private String Time;

	public int getTripid() {
		return Tripid;
	}

	public void setTripid(int tripid) {
		Tripid = tripid;
	}


	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getDeponame() {
		return deponame;
	}

	public void setDeponame(String deponame) {
		this.deponame = deponame;
	}

    
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTripno() {
		return tripno;
	}

	public void setTripno(int tripno) {
		this.tripno = tripno;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	@Override
	public String toString() {
		return "ConTrip [Tripid=" + Tripid + ", bno=" + bno + ", deponame=" + deponame + ", num=" + num + ", tripno="
				+ tripno + ", date=" + date + ", Time=" + Time + "]";
	}

	
	
}
