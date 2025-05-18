package com.microservice.shopingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private int addressId;
	private String doorNo;
	private String streetName;
	private String layout;
	private String city;
	private String pincode;
}
