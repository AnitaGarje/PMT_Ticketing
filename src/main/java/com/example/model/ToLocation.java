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
public class ToLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tolocid")
	private int tolocid;
	
    @Column(name = "toloc",unique = true)
	private String tooloc;

	public int getTolocid() {
		return tolocid;
	}

	public void setTolocid(int tolocid) {
		this.tolocid = tolocid;
	}

	public String getTooloc() {
		return tooloc;
	}

	public void setTooloc(String tooloc) {
		this.tooloc = tooloc;
	}

	
}