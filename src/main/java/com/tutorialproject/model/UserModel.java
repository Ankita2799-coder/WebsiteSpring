package com.tutorialproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserModel 
{
	@Id
	private String email;
	private String name;
	private String username;
	private String cpassword;
	
	public UserModel(String email, String name, String username, String cpassword, String password) {
		super();
		this.email = email;
		this.name = name;
		this.username = username;
		this.cpassword = cpassword;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	public UserModel() {
		super();
	}
	public UserModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
}
