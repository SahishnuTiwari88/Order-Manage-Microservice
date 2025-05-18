package com.microservice.shopingservice.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private int customerId;
	private String customerName;
	private String customerEmail;
	//@OneToOne(cascade = CascadeType.ALL)
	
	private Address customerBillingAddress;
	//@OneToOne(cascade = CascadeType.ALL)
	
	private Address customerShippingAddress;
	private int orderId;
	private int cartId;
	//private Cart cart;
	private List<LineItem>lineItem;
	//private Order order;
	

	
}
