package com.wipro.SA20449357.microservices_order.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="Order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	@Transient
	private List<LineItem> lineItem = new ArrayList<>();
//	@Transient
//	private Cart cart;

}
