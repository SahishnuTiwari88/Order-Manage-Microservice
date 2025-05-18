package com.wipro.SA20449357.microservicesproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MicroservicesCapstoneProductCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCapstoneProductCatalogueApplication.class, args);
	}

}
