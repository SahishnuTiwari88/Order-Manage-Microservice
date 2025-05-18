package com.wipro.SA20449357.microservices_cart.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	private int customerId;
	//private int orderId;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<LineItem> lineItem = new ArrayList<>();

	
	//method to add line item to cart
	public void addLineItem(LineItem lineItem) {
        lineItem.setCart(this);
        this.getLineItem().add(lineItem);
    }
}
