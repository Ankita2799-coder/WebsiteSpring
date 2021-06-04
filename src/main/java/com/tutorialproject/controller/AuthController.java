package com.tutorialproject.controller;
import java.security.Principal;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialproject.Service.UserDetailsServiceImp;
import com.tutorialproject.Service.mailService;
import com.tutorialproject.configuration.JwtUtil;
import com.tutorialproject.model.JwtRequest;
import com.tutorialproject.model.JwtResponse;
import com.tutorialproject.model.UserModel;
import com.tutorialproject.model.otp;
import com.tutorialproject.repository.UserRepo;
import com.tutorialproject.repository.otpRepository;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImp UserDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
    @Autowired
    private mailService mailService;
    @Autowired
	private otpRepository otpRepo;
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
	
	@PostMapping("register/{otp}")
	public ResponseEntity<?> signUser(@RequestBody UserModel umodel,@PathVariable("otp") String otp){
//		System.out.println(umodel.getEmail());
		//check existing user
		UserModel um=userRepo.findByEmail(umodel.getEmail());
		if(um == null)
		{
			//validate otp
			otp	dbOtp=otpRepo.findByEmail(umodel.getEmail());
			if(dbOtp==null)
			{
				System.out.println("enter valid otp");
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Enter Valid Otp");
			}
			System.out.println((dbOtp.getOtpRequestedTime().getTime()+300000)/60000);
			System.out.println(System.currentTimeMillis()/60000);
			
			if(dbOtp.getOtp().equals(otp) && dbOtp.getOtpRequestedTime().getTime()+300000>=System.currentTimeMillis())
			{
			 userRepo.save(umodel);
			 return new ResponseEntity<Object>(202, HttpStatus.OK);
			}
			else {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Otp Expired Request new one");
			}
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
	
	@GetMapping("/generate-otp/{email}")
  @Transactional
	public ResponseEntity<?> generateOtp(@PathVariable("email") String email)
	{
		int[] otpl=mailService.generateOTP();
		String otp="";
		for(int l:otpl)
		{
			otp+=""+l;
		}
		try {
		mailService.sendEmailsignup(otp, "OTP For Signup", email);
		System.out.println("email sent to"+email);
		}
		catch(Exception e){
			System.out.println("email not sent to"+email);
			return new ResponseEntity(401,HttpStatus.NOT_IMPLEMENTED);
		}
		//if email sent
		otp otpobj=new otp();
		otp otpexist=otpRepo.findByEmail(email);
		if(otpexist==null) {
		otpobj.setEmail(email);
		otpobj.setOtp(otp);
		otpobj.setOtpRequestedTime(new Date());
		otpRepo.save(otpobj);
		}
		else {
			otpobj.setEmail(email);
			otpobj.setOtp(otp);
			otpobj.setOtpRequestedTime(new Date());
			otpRepo.updateByEmail(otp,new Date(),email);
		}
		return new ResponseEntity(200,HttpStatus.OK);
		
	}
	

}
