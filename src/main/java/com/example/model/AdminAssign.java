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
@Table(name = "AdminAssign")
public class AdminAssign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Assignid")
	private int assignid;
	
	@Column(name = "AID")
	private int aid;

	@Column(name = "Name")
	@NotEmpty(message = "*Please provide name")
	private String name;
	
	@Column(name = "Bno")
	private String bno;
	
	@Column(name = "DepoName")
	@NotEmpty(message = "*Please provide depot name")
	private String deponame;
	
	@Column(name = "Route")
	@NotEmpty(message = "*Please provide route of bus")
	private String route;
	
	@Column(name = "Busno")
	private String busno;
	
	@Column(name = "Tripno")
	private String tripno;
	
	@Column(name = "Date")
	private String date;
	
	@Column(name = "Time")
	private String Time;

	int getAssignid() {
		return assignid;
	}

	void setAssignid(int assignid) {
		this.assignid = assignid;
	}

	int getAid() {
		return aid;
	}

	void setAid(int aid) {
		this.aid = aid;
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

	String getDeponame() {
		return deponame;
	}

	void setDeponame(String deponame) {
		this.deponame = deponame;
	}

	String getRoute() {
		return route;
	}

	void setRoute(String route) {
		this.route = route;
	}

	String getBusno() {
		return busno;
	}

	void setBusno(String busno) {
		this.busno = busno;
	}

	String getTripno() {
		return tripno;
	}

	void setTripno(String tripno) {
		this.tripno = tripno;
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
