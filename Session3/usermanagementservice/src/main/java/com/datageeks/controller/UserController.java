package com.datageeks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.datageeks.dao.User;
import com.datageeks.service.ServicessException;
import com.datageeks.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService = null;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		try {
			userService.save(user);
		} catch (ServicessException exp) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user) {
		try {
			userService.update(user);
		} catch (ServicessException exp) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> userList = null;
		try {
			userList = userService.getAll();
		} catch (ServicessException exp) {
			return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<User>>(userList, HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") String userId) {
		User user = null;
		try {
			user = userService.get(userId);
		} catch (ServicessException exp) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") String userId) {
		try {
			userService.delete(userId);
		} catch (ServicessException exp) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

}
