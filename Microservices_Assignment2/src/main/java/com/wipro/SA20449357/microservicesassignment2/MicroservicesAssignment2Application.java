package com.wipro.SA20449357.microservicesassignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MicroservicesAssignment2Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesAssignment2Application.class, args);
	}

}
