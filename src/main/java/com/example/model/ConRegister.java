package com.example.model;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "conregister")
public class ConRegister {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cid")
	private int cid;
	

	/*@OneToMany(cascade = CascadeType.ALL , mappedBy = "ConRegister")
	private Set<ConTrip> conTrips = new HashSet <ConTrip>();
	
	public Set<ConTrip> getConTrips() {
		return conTrips;
	}

	public void setConTrips(Set<ConTrip> conTrips) {
		this.conTrips = conTrips;
	}
	*/

	@Column(name = "name")
	private String name;
	
	@Column(name = "bno")
	private String bno;
	
	@Column(name = "phoneno")
	private String phoneno;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	@Override
	public String toString() {
		return "ConRegister [cid=" + cid + ", name=" + name + ", bno=" + bno + ", phoneno=" + phoneno + "]";
	}

}
