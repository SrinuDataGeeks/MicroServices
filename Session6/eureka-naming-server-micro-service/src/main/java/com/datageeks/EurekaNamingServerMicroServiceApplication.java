package com.datageeks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaNamingServerMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaNamingServerMicroServiceApplication.class, args);
	}

}
