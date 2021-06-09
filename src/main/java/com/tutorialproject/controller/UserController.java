package com.tutorialproject.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialproject.model.UserModel;
import com.tutorialproject.repository.UserRepo;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	@Transactional
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody UserModel model)
	{
		System.out.println(model.getUsername() + model.getEmail() + model.getName() + model.getPhone());
		userRepo.update(model.getName(), model.getPhone(), model.getEmail(), model.getUsername());
		return new ResponseEntity(200,HttpStatus.OK);
	}
	@Transactional
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody UserModel model)
	{
		System.out.println(model.getPassword());
		userRepo.updatePassword(model.getPassword(), model.getUsername());
		return new ResponseEntity(200,HttpStatus.OK);
		
	}

}
