package com.microservice.shopingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;

}
