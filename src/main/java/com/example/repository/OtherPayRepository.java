package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.OtherPay;

@Repository("otherPayRepository")
public interface OtherPayRepository extends JpaRepository <OtherPay, Long>{

}


