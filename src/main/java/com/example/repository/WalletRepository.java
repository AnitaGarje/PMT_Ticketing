package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Passenger;
import com.example.model.Wallet;

@Repository("walletRepository")
public interface WalletRepository extends JpaRepository <Wallet, Long>{
	Wallet findByPassenger(Passenger passenger);
	//Wallet createWallet(Wallet Wallet);
  
}
