package com.datageeks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerServiceApplication.class, args);
	}

}
