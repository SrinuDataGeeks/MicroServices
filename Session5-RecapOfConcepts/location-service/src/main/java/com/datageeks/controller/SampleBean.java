package com.datageeks.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SampleBean {
	
	@Value("${location-service.message}")
	private String message = null;
	
	
	@Value("${location-service.job}")
	private String job = null;


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}
	
	

}
