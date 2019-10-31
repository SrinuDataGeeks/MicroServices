package com.datageeks.controller;

import java.util.List;

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

	@PostMapping
	public ResponseEntity<UserAccount> save(@RequestBody UserAccount user) {
		try {
			userAccountService.save(user);
		} catch (ServicessException exp) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserAccount>(user, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserAccount> update(@RequestBody UserAccount user) {
		try {
			userAccountService.update(user);
		} catch (ServicessException exp) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserAccount>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserAccount>> getAll() {
		List<UserAccount> userList = null;
		try {
			userList = userAccountService.getAll();
		} catch (ServicessException exp) {
			return new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info(" *********  environment ********"+environment);
		return new ResponseEntity<List<UserAccount>>(userList, HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserAccount> getById(@PathVariable("id") String userId) {
		UserAccount user = null;
		try {
			user = userAccountService.get(userId);
		} catch (ServicessException exp) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserAccount>(user, HttpStatus.OK);
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
