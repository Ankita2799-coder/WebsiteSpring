package com.tutorialproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorialproject.model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, String> {
	UserModel findByEmailAndPassword(String email, String password);
	UserModel findByEmail(String email);

}
