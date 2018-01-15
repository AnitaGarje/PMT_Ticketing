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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "conTktbook")


public class ConTktbook {
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "otpid")
		private int otpid;
		
	    @Column(name = "otp")
		@NotEmpty(message = "*Please enter recieved otp")
		private String otp;

		public int getOtpid() {
			return otpid;
		}

		public void setOtpid(int otpid) {
			this.otpid = otpid;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}
	  
	  }

