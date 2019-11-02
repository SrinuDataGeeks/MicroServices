package com.datageeks.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "google-map-service")
@RibbonClient(name = "google-map-service")
public interface GoogleMapsFeignProxy {

	@GetMapping("/googlemaps/config")
	public abstract ResponseEntity<GoogleWithSampleInfo> getGoogleConfigInfo();
}
	