package com.datageeks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableEurekaServer
public class EurekaNamingServerMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaNamingServerMicroServiceApplication.class, args);
	}


	@Bean
	public Sampler defaultSampler()
	{
		return Sampler.ALWAYS_SAMPLE;
	}
	
}
