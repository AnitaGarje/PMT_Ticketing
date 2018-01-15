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
@Table(name = "conlogin")

public class ConLogin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "loginid")
	private int loginid;

	@Column(name = "phoneno")
	private String phoneno;

	/*@ManyToOne (cascade = CascadeType.ALL)
	private ConRegister conRegister;
	
	public ConRegister getConRegister() {
		return conRegister;
	}

	public void setConRegister(ConRegister conRegister) {
		this.conRegister = conRegister;
	}*/

	public int getLoginid() {
		return loginid;
	}

	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	
	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	
	

}
