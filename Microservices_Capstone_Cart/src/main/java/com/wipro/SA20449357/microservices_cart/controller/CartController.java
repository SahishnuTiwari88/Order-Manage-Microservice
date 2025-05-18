package com.wipro.SA20449357.microservices_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.shopingservice.entities.CustomerCartMapping;
import com.wipro.SA20449357.microservices_cart.entities.Cart;
import com.wipro.SA20449357.microservices_cart.entities.LineItem;
import com.wipro.SA20449357.microservices_cart.service.CartService;


@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartServ;
	
	
	@PostMapping
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart){
		
		Cart addCart = cartServ.addCart(cart);
		return ResponseEntity.status(HttpStatus.CREATED).body(addCart);
	}
	//working
	@PostMapping("/{cartId}")
	public ResponseEntity<Cart> addCartId(@PathVariable int cartId){
		
		Cart addCartId = cartServ.addCartId(cartId);
		return ResponseEntity.status(HttpStatus.CREATED).body(addCartId);
	}
	
//	@PostMapping("/addItem/{itemId}")
//	public ResponseEntity<?> addItemId(@PathVariable int itemId){
//		LineItem addItemId = cartServ.addItemId(itemId);
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(addItemId);
//	}
	
	
	//working
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable int id) {
		cartServ.emptyCart(id);
		return ResponseEntity.ok("Cart deleted");
	}
	
	//working
	@PutMapping("/{id}")
	public ResponseEntity<Cart> updateCart(@RequestBody Cart cart, @PathVariable int id){
		Cart updateCart = cartServ.updateCart(cart, id);
		return ResponseEntity.ok(updateCart);
		
//		{
//		    "cartId": 4,
//		    "lineItem": [
//		        {
//		            "itemId": 4,
//		            "productId": 3,
//		            "productName": "Samsung s21",//here send data in this form
//		            "quantity": 1,
//		            "price": 45000
//		        }
//		    ]
//		}
	}
	
	//working
	@GetMapping("/{id}")
	public ResponseEntity<Cart> searchCart(@PathVariable int id){
		Cart searchCart = cartServ.searchCart(id);
		return ResponseEntity.ok(searchCart);
	}
	
	//now for LineItem part
	@PostMapping("/lineItem/{cartId}")
	public ResponseEntity<Cart> addLineItem(@PathVariable int cartId,@RequestBody LineItem item){
		Cart addLineItem = cartServ.addLineItem(item, cartId);
		return ResponseEntity.status(HttpStatus.CREATED).body(addLineItem);
		
	}
	
	@DeleteMapping("/{cartId}/{itemId}")
	public ResponseEntity<?> deleteLineItem(@PathVariable int cartId,@PathVariable int itemId){
		cartServ.deleteLineItem(cartId, itemId);
		return ResponseEntity.ok("Deleted...");
	}
	
	@PutMapping("/updateItem/{cartId}/{itemId}")
	public ResponseEntity<Cart> updateLineItem(@PathVariable int cartId,@PathVariable
			int itemId,@RequestBody LineItem item){
		Cart updateLineItem = cartServ.updateLineItem(cartId, itemId, item);
		return ResponseEntity.ok(updateLineItem);
	}
	
	@GetMapping("/{cartId}/{itemId}")
	public ResponseEntity<Cart> searchLineItem(@PathVariable int cartId,
			@PathVariable int itemId){
		Cart cart = cartServ.searchLineItem(cartId, itemId);
		
			return ResponseEntity.ok(cart);
		

	}
	
	@PostMapping("/customer-cartmap")
	public ResponseEntity<?> saveCustomerCartMapping(@RequestBody CustomerCartMapping mapping){
		cartServ.saveCustomerCartMaping(mapping);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cart-customer mapping created");
	}
	

	
	@GetMapping("/customer/{customerId}")
    public ResponseEntity<Cart> getCartIdForCustomer(@PathVariable int customerId) {
         Cart cartIdForCustomer = cartServ.getCartIdForCustomer(customerId);
        return ResponseEntity.ok(cartIdForCustomer);
    }
	
	@PutMapping("/{cartId}/lineItem")
    public ResponseEntity<Cart> addLineItemToCart(@RequestBody LineItem lineItem, @PathVariable int cartId) {
        Cart cart = cartServ.addLineItemToCart(cartId, lineItem);
        return ResponseEntity.ok(cart);
    }
	

	}
