package com.wipro.SA20449357.microservices_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MicroservicesCapstoneCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCapstoneCartApplication.class, args);
	}

}
