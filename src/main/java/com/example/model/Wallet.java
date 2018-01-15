package com.example.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "walletid")
	private int walletid;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Passenger passenger;
	

	@Column(name = "avalbal")
	private double avalbal;
	
	@Column(name = "addamt")
	private double addamt;

	public int getWalletid() {
		return walletid;
	}

	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}

	public double getAvalbal() {
		return avalbal;
	}

	public void setAvalbal(double avalbal) {
		this.avalbal = avalbal;
	}
	
	public double getAddamt() {
		return addamt;
	}

	public void setAddamt(double addamt) {
		this.addamt = addamt;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	@Override
	public String toString() {
		return "Wallet [walletid=" + walletid + ", passenger=" + passenger + ", avalbal=" + avalbal + ", addamt="
				+ addamt + "]";
	}



    

	
}
