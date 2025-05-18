package com.microservice.shopingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.shopingservice.entities.Customer;
import com.microservice.shopingservice.entities.LineItem;
import com.microservice.shopingservice.entities.Product;
import com.microservice.shopingservice.service.CartService;
import com.microservice.shopingservice.service.CustomerService;
import com.microservice.shopingservice.service.OrderService;
import com.microservice.shopingservice.service.ProductService;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {
	@Autowired
	private ProductService productServ;
	@Autowired
	private CustomerService customerServ;
	@Autowired
	private CartService cartServ;
	@Autowired
	private OrderService orderServ;
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product createProduct = productServ.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable int productId){
		Product product = productServ.getProductFromProductService(productId);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		Customer createCustomer = customerServ.createCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(createCustomer);
		
		/* {
   
    "customerName": "Viraj",
    "customerEmail": "Viraj@gmail.com",
    "customerBillingAddress": {
        
        "doorNo": "007 XV",
        "streetName": "4th cross",
        "layout": "Spice",
        "city": "Bangalore",
        "pincode": "560099"
    },
    "customerShippingAddress": {
        
        "doorNo": "007 XV",
        "streetName": "4th cross",
        "layout": "Spice",
        "city": "Bangalore",
        "pincode": "560099"
    }
} data send in this form in order to create new customer and cart id*/
	}

	
	@PutMapping("/customer/{customerId}/cart")
	public void addItemByCustomerId(@PathVariable int customerId,@RequestBody LineItem item){
		 cartServ.addLineItemToCart(item, customerId);
		 /* 
		  {
    "productId":10,
    "productName":"Moto G-60",//by customer id it will put line iten in cart service lineitem
    "quantity":2,
    "price":40000
} 
		  */
		
	}
	
	@PostMapping("/customer/{customerId}/{orderId}")
	public ResponseEntity<?> getCustomerId(@PathVariable int customerId,@PathVariable int orderId) {
		orderServ.findCustomer(customerId, orderId);
		return ResponseEntity.ok("Order id created");
	}
	
	@GetMapping("/customer/{customerId}/order")
	public ResponseEntity<Customer> getCustomerOrderDetails(@PathVariable int customerId) {
		Customer customerOrderDetail = orderServ.getCustomerOrderDetail(customerId);
		return ResponseEntity.ok(customerOrderDetail);
	}

}
