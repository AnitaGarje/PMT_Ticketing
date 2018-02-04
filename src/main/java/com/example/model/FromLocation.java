package com.example.model;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "Fromlocation")
public class FromLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fromlocid")
	private int fromlocid;
	
    @Column(name = "fromloc",unique = true)
	private String fromloc;

	public int getFromlocid() {
		return fromlocid;
	}

	public void setFromlocid(int fromlocid) {
		this.fromlocid = fromlocid;
	}

	public String getFromloc() {
		return fromloc;
	}

	public void setFromloc(String fromloc) {
		this.fromloc = fromloc;
	}



		
}