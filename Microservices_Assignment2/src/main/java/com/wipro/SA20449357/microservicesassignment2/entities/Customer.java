package com.wipro.SA20449357.microservicesassignment2.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String customerName;
	private String customerEmail;
	private int orderId;
	@OneToOne(cascade = CascadeType.ALL)
	
	private Address customerBillingAddress;
	@OneToOne(cascade = CascadeType.ALL)
	
	private Address customerShippingAddress;
}
