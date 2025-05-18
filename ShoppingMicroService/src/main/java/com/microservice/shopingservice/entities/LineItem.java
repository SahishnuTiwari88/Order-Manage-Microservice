package com.microservice.shopingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
	@JsonProperty("itemId")
	private int itemId;
	
	@JsonProperty("productId")
	private int productId;
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("quantity")
	private int quantity;
	
	@JsonProperty("price")
	private double price;

}
