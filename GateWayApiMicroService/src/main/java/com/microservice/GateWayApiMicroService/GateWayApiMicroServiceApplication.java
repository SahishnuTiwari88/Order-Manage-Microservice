package com.microservice.GateWayApiMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class GateWayApiMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApiMicroServiceApplication.class, args);
	}

}
