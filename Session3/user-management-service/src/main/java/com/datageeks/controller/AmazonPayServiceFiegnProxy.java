package com.datageeks.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "amazon-pay-service")
@RibbonClient(name = "amazon-pay-service")
public interface AmazonPayServiceFiegnProxy {

	@PostMapping("/userAccounts/{userId}")
	public abstract ResponseEntity<UserAccount> saveAccountDetails(@PathVariable("userId") String userId);
	
	@DeleteMapping("/userAccounts/{userId}")
	public abstract ResponseEntity<HttpStatus> deleteAccountDetails(@PathVariable("userId") String userId);
}
