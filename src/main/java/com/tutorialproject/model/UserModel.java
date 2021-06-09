package com.tutorialproject.model;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
public class UserModel implements UserDetails
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 int user_id;
	@Column(name="email",length=50)
	String email;
	@Column(name="name",length=50)
	 String name;
	@Column(name="username",length=50)
	 String username;
	@Column(name="password",length=50)
	 String password;
	@Column(name="phone",length=50)
	 String phone;
	@Column(name="cpassword",length=50)
	 String cpassword;
	public UserModel(int user_id, String email, String name, String username, String password, String phone,
			String cpassword) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.cpassword = cpassword;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UserModel() {
		super();
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Authority> auth=new HashSet<>();
		auth.add(new Authority("ROLE_USER"));
		return auth;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}