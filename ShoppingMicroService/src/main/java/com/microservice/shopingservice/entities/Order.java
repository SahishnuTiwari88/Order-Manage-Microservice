package com.microservice.shopingservice.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
private int orderId;
private int cartId;
private List<LineItem> lineItem;
}
