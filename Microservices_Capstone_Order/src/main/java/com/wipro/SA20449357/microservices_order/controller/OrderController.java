package com.wipro.SA20449357.microservices_order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.SA20449357.microservices_order.entities.LineItem;
import com.wipro.SA20449357.microservices_order.entities.Order;
import com.wipro.SA20449357.microservices_order.service.OrderService;
@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<Order> add(@RequestBody Order order){
		
		Order add1 = orderService.add(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(add1);
	}
	
	@PostMapping("/{orderId}")
	public ResponseEntity<Order> addOrderId(@PathVariable int orderId){
		
		Order addOrderId = orderService.addOrderId(orderId);
		return ResponseEntity.status(HttpStatus.CREATED).body(addOrderId);
	}
	
	
	@PostMapping("/{cartId}/{itemId}/{orderId}")
	public ResponseEntity<Order> addOrder(@PathVariable int cartId,@PathVariable int itemId,
			@PathVariable int orderId ,@RequestBody LineItem item){
		Order addOrder = orderService.addOrder(cartId,itemId,orderId,item);
		return ResponseEntity.ok(addOrder);
		
		
//		 {
//	            "itemId": 8,
//	            "productId": 4,
//	            "productName": "Samsung Galaxy 231",
//	            "quantity": 1,
//	            "price": 60000//send data in this form for that cart id must already available
//	        }
	}
	//working
	@DeleteMapping("/{cartId}/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable int cartId, @PathVariable int orderId){
		orderService.deleteOrder(cartId, orderId);
		return ResponseEntity.ok("Order and releted data deleted");
		
	}
	
	@PutMapping("/{cartId}/{itemId}/{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable int cartId, @PathVariable int itemId,
			@PathVariable int orderId,@RequestBody LineItem item){
		Order updateOrder = orderService.updateOrder(cartId, itemId, orderId, item);
		return ResponseEntity.ok(updateOrder);
		
//		{
//            "itemId": 2,
//            "productId": 1,
//            "productName": "iPhone 11",
//            "quantity": 1,
//            "price": 45000 //data send in this way from postman with cartid,itemid,orderid
//        }
	}
	@GetMapping("/getdata/{orderId}/{cartId}")
	public ResponseEntity<Order> searchOrder(@PathVariable int orderId,@PathVariable int cartId){
		Order searchOrder = orderService.searchOrder(orderId,cartId);
		return ResponseEntity.ok(searchOrder);
	}


}
