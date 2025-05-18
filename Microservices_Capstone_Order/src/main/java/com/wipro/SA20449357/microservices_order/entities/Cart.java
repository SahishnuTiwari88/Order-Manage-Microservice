package com.wipro.SA20449357.microservices_order.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cart {

	private int cartId;
	private List<LineItem> lineItem;
}

