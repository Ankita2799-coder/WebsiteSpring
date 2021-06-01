package com.tutorialproject.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialproject.Service.UserDetailsServiceImp;
import com.tutorialproject.configuration.JwtUtil;
import com.tutorialproject.model.JwtRequest;
import com.tutorialproject.model.JwtResponse;
import com.tutorialproject.model.UserModel;
import com.tutorialproject.repository.UserRepo;

@RestController
@CrossOrigin(origins = "*")
public class TutController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImp UserDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;

    @RequestMapping(value="/tutologin", method = { RequestMethod.POST })
 	public ResponseEntity<?> getUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
    	 System.out.println("USername"+authenticationRequest.getUsername()+"Password"+authenticationRequest.getPassword());
    	 //Authentication Happens here
    	 try {
    	System.out.println("authentication started");
    	 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    	 System.out.println("authenticatiion performed successfully");
    	 }
    	 catch(BadCredentialsException e)
    	 {
    		 throw new Exception("Incorrect username or password"+e);
    	 }
    	 //Creation of jwt
    	 System.out.println("creation of jwt");
    	 final UserDetails userDetails=UserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    	 System.out.println("fetched userdetails");
    	 final String jwt=jwtTokenUtil.generateToken(userDetails);
    	 System.out.println("token generated"+jwt);
    	 // returning jwt
    	 return ResponseEntity.ok(new JwtResponse(jwt));
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
	@GetMapping("/current-user")
	public UserModel getCurrentLoginedUser(Principal principal)
	{
		return (UserModel)UserDetailsService.loadUserByUsername(principal.getName());
	}
	

}
