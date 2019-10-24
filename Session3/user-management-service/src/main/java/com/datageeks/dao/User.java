package com.datageeks.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERINFO")
public class User implements Serializable {

	@Id
	@Column(name = "USER_ID")
	private String Userid = null;
	@Column(name = "PASSWORD")
	private String password = null;
	@Column(name = "FIRST_NAME")
	private String firstName = null;
	@Column(name = "LAST_NAME")
	private String lastName = null;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
