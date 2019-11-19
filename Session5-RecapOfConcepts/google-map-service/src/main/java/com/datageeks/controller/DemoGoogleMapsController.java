package com.datageeks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger log = LoggerFactory.getLogger(DemoGoogleMapsController.class);
@Autowired
private GoogleInfoBean googleInfoBean = null;



@Autowired
private Environment environment = null;
@GetMapping("/config")
public ResponseEntity<GoogleInfoBean> getConfigData()
	{
	log.info(" ******googleInfoBean "+googleInfoBean);
		return new ResponseEntity<GoogleInfoBean>(googleInfoBean,HttpStatus.OK);
	}
}
