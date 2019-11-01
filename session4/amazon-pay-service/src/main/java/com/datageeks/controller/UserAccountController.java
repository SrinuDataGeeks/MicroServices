package com.datageeks.controller;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datageeks.dao.UserAccount;
import com.datageeks.dao.UserTransactions;
import com.datageeks.service.ServicessException;
import com.datageeks.service.UserAccountService;

@RestController
@RequestMapping("/userAccounts")
public class UserAccountController {

	Logger log = LoggerFactory.getLogger(UserAccountController.class);

	@Autowired
	private UserAccountService userAccountService = null;

	@Value("${usermanagementservice-env}")
	private String environment = null;

	@PostMapping(path = "/{userId}")
	public ResponseEntity<UserAccount> save(@PathVariable("userId") String userId) {
		UserAccount userAccount = new UserAccount();
		try {
			userAccount.setUserId(userId);
			userAccount.setBalance(0f);
			userAccount.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			userAccountService.save(userAccount);
		} catch (ServicessException exp) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserAccount>(userAccount, HttpStatus.CREATED);
	}

	@PutMapping(path = "/addAmount/{userId}/{amount}")
	public ResponseEntity<UserAccount> update(@PathVariable("userId") String userId , @PathVariable("amount") Float amount) {
		UserAccount userAccount = null;
		
		try {
			userAccount = userAccountService.get(userId);
			if(userAccount == null)
			{
				return new ResponseEntity<UserAccount>(HttpStatus.NO_CONTENT);
			}
			userAccount.setBalance(userAccount.getBalance() + amount);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			userAccount.setLastUpdated(currentTime);
			

			
			userAccountService.update(userAccount);
			
			UserTransactions userTransaction = new UserTransactions();
			userTransaction.setAmountTransfered(amount);
			userTransaction.setTxnType("Credit");
			userTransaction.setDateAndTimeUpdate(currentTime);
			userTransaction.setUserAccount(userAccount);
			
			userAccountService.save(userTransaction);
			
			Set<UserTransactions> txnSet = userAccount.getUserTransactionsSet();
			if(txnSet == null)
			{
				txnSet = new LinkedHashSet<UserTransactions>();
			}
			txnSet.add(userTransaction);
			userAccount.setUserTransactionsSet(txnSet);
			
		} catch (ServicessException exp) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserAccount>> getAll() {
		List<UserAccount> userAccountList = null;
		try {
			userAccountList = userAccountService.getAll();
		} catch (ServicessException exp) {
			return new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info(" *********  environment ********" + environment);
		return new ResponseEntity<List<UserAccount>>(userAccountList, HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserAccount> getById(@PathVariable("id") String userId) {
		UserAccount userAccount = null;
		try {
			userAccount = userAccountService.get(userId);
			if(userAccount == null)
			{
				return new ResponseEntity<UserAccount>(HttpStatus.NO_CONTENT);
			}
		} catch (ServicessException exp) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") String userId) {
		try {
			userAccountService.delete(userId);
		} catch (ServicessException exp) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

}
