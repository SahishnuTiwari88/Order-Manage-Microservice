package com.wipro.SA20449357.microservices_cart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	private int productId;
	private String productName;
	private int quantity;
	private double price;
	@ManyToOne
	@JsonBackReference
	private Cart cart;

}
