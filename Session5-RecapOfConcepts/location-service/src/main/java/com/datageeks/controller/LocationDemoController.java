package com.datageeks.controller;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/locations")
public class LocationDemoController {
	
	Logger log = LoggerFactory.getLogger(LocationDemoController.class );

	@Autowired
	private SampleBean sampleBean = null;
	
	@Autowired
	private GoogleMapsFeignProxy googleProxy = null;

	@GetMapping("/config")
	public ResponseEntity<SampleBean> loadConfigData() {
		log.info(" **** sampleBean ****  "+sampleBean);
		return new ResponseEntity<SampleBean>(sampleBean, HttpStatus.OK);
	}
	
	@GetMapping("/fault-tolerance")
	@HystrixCommand(fallbackMethod = "fallbackLoadConfigDataHytrix")
		public ResponseEntity<SampleBean> loadConfigDataHytrix() {
		
		throw new RuntimeException(" Hytrix Demo !!!");
		//return new ResponseEntity<SampleBean>(sampleBean, HttpStatus.OK);
	}
	
	public ResponseEntity<SampleBean>  fallbackLoadConfigDataHytrix() 
	{
		SampleBean defaultResp = new SampleBean();
		defaultResp.setJob("TEST111");
		defaultResp.setMessage("Response from Hytrix");
		
		return new ResponseEntity<SampleBean>(defaultResp, HttpStatus.OK);
	}

	@GetMapping("/google/config")
	public ResponseEntity<GoogleWithSampleInfo> loadAllConfig() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GoogleWithSampleInfo> resEntity = restTemplate
				.getForEntity("http://localhost:8102/googlemaps/config", GoogleWithSampleInfo.class);
		GoogleWithSampleInfo allInfo = resEntity.getBody();
		allInfo.setMessage(sampleBean.getMessage());
		allInfo.setJob(sampleBean.getJob());
		
			return new ResponseEntity<GoogleWithSampleInfo>(allInfo, HttpStatus.OK);
	}
	
	@GetMapping("/google/config/feign-client")
	public ResponseEntity<GoogleWithSampleInfo> loadAllConfig2() {

		ResponseEntity<GoogleWithSampleInfo> resEntity = googleProxy.getGoogleConfigInfo();
		GoogleWithSampleInfo allInfo = resEntity.getBody();
		allInfo.setMessage(sampleBean.getMessage());
		allInfo.setJob(sampleBean.getJob());

		return new ResponseEntity<GoogleWithSampleInfo>(allInfo, HttpStatus.OK);
	}

}
