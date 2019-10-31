package com.datageeks.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount implements Serializable {

	@Id
	@Column(name = "USER_ID")
	private String userId = null;
	
	@Column(name = "BALANCE")
	private Float balance = null;
	
	@Column(name = "LAST_UPDATED")
	private Timestamp lastUpdated = null;
	
	
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name = "USER_FK_ID")
	private Set<UserTransactions> userTransactionsSet = null;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Float getBalance() {
		return balance;
	}


	public void setBalance(Float balance) {
		this.balance = balance;
	}


	public Timestamp getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public Set<UserTransactions> getUserTransactionsSet() {
		return userTransactionsSet;
	}


	public void setUserTransactionsSet(Set<UserTransactions> userTransactionsSet) {
		this.userTransactionsSet = userTransactionsSet;
	}
	
	

}
