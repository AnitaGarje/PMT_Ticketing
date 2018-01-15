package com.example.model;

public class WalletDebit {
	
	private Wallet wallet;
	
	private DebitCard debitCard;
	
	public Wallet getWallet() {
		return wallet;
	}
	public void  setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public DebitCard getDebitCard() {
		return debitCard;
	}
	public void setDebitCard(DebitCard debitCard) {
		this.debitCard = debitCard;
	}
	
}
