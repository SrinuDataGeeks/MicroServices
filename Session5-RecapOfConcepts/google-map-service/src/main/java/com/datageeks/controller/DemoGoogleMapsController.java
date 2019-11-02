package com.datageeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/googlemaps")
public class DemoGoogleMapsController {
@Autowired
private GoogleInfoBean googleInfoBean = null;

@Autowired
private Environment environment = null;
@GetMapping("/config")
public ResponseEntity<GoogleInfoBean> getConfigData()
	{
	System.out.println(" *********Google Map Service Port ***** "+environment.getProperty("local.server.port"));
		return new ResponseEntity<GoogleInfoBean>(googleInfoBean,HttpStatus.OK);
	}
}
