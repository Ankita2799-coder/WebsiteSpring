package com.tutorialproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialproject.model.UserModel;
import com.tutorialproject.repository.UserRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TutController {
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("tutologin")
	public UserModel loginUser(@RequestBody UserModel umodel){
		System.out.println(umodel.getEmail());
		return userRepo.findByEmailAndPassword(umodel.getEmail(), umodel.getPassword());
	}
	
	@PostMapping("tutosign")
	public ResponseEntity<?> signUser(@RequestBody UserModel umodel){
//		System.out.println(umodel.getEmail());
		UserModel um=userRepo.findByEmail(umodel.getEmail());
		if(um == null)
		{
			 userRepo.save(umodel);
			 return new ResponseEntity<Object>(202, HttpStatus.OK);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Email already Exists");
		}
		
	}
	
	public UserRepo getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

}
