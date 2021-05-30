package com.tutorialproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorialproject.model.UserModel;
@Repository
public interface UserRepo extends JpaRepository<UserModel, String> {
	UserModel findByEmailAndPassword(String email, String password);
	UserModel findByEmail(String email);
	UserModel findByUsername(String username);

}
