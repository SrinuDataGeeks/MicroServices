package com.datageeks.dao;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER_TRANSACTIONS")
public class UserTransactions {

	@Id
	@GenericGenerator(name = "icr", strategy = "increment")
	@GeneratedValue(generator = "icr")
	@Column(name = "TXN_ID")
	private Integer transactionId = null;

	@Column(name = "AMOUNT_TRANSFERED")
	private Float amountTransfered = null;

	@Column(name = "DATE_TIME")
	private Timestamp dateAndTimeUpdate = null;
	
	@Column(name = "TXN_TYPE")
	private String txnType = null;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_FK_ID")
	@JsonBackReference
	private UserAccount userAccount = null;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Float getAmountTransfered() {
		return amountTransfered;
	}

	public void setAmountTransfered(Float amountTransfered) {
		this.amountTransfered = amountTransfered;
	}

	public Timestamp getDateAndTimeUpdate() {
		return dateAndTimeUpdate;
	}

	public void setDateAndTimeUpdate(Timestamp dateAndTimeUpdate) {
		this.dateAndTimeUpdate = dateAndTimeUpdate;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

}
