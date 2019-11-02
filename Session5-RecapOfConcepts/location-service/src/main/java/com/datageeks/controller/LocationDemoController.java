package com.datageeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/locations")
public class LocationDemoController {

	@Autowired
	private SampleBean sampleBean = null;
	
	@Autowired
	private GoogleMapsFeignProxy googleProxy = null;

	@GetMapping("/config")
	public ResponseEntity<SampleBean> loadConfigData() {
		return new ResponseEntity<SampleBean>(sampleBean, HttpStatus.OK);
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
