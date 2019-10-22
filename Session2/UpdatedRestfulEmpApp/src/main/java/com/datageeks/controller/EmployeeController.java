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

import com.datageeks.dao.EmployeeBean;
import com.datageeks.service.EmployeeService;
import com.datageeks.service.ServicesException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService = null;

	@GetMapping
	public ResponseEntity<List<EmployeeBean>> getAll() {
		List<EmployeeBean> employeeList = null;
		try {
			employeeList = employeeService.getAll();
		} catch (ServicesException e) {
			return new ResponseEntity<List<EmployeeBean>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<EmployeeBean>>(employeeList, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EmployeeBean> save(@RequestBody EmployeeBean employeeBean) {
		try {
			employeeService.saveOrUpdate(employeeBean);
		} catch (ServicesException e) {
			return new ResponseEntity<EmployeeBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<EmployeeBean>(employeeBean, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<EmployeeBean> update(@RequestBody EmployeeBean employeeBean) {
		try {
			employeeService.saveOrUpdate(employeeBean);
		} catch (ServicesException e) {
			return new ResponseEntity<EmployeeBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<EmployeeBean>(employeeBean, HttpStatus.OK);
	}

	@GetMapping(path = "/{empId}")
	public ResponseEntity<EmployeeBean> getById(@PathVariable("empId") Integer empId) {
		EmployeeBean employee = null;
		try {
			employee = employeeService.getById(empId);
		} catch (ServicesException e) {
			return new ResponseEntity<EmployeeBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<EmployeeBean>(employee, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{empId}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("empId") Integer empId) {
		try {
			employeeService.delete(empId);
		} catch (ServicesException e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
