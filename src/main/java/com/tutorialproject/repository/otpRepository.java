package com.tutorialproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorialproject.model.otp;
@Repository
public interface otpRepository extends JpaRepository<otp, Integer> {
   

}
