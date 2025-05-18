package com.microservice.shopingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.shopingservice.entities.Cart;
import com.microservice.shopingservice.entities.Customer;
import com.microservice.shopingservice.entities.CustomerCartMapping;

@Service
public class CustomerService {
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Customer createCustomer(Customer customer) {
		//adding new customer
		String customerServiceUrl = "http://CUSTOMER-SERVICE/api/addCustomer";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> customerEntity = new HttpEntity<>(customer,headers);
		ResponseEntity<Customer> addCustomer = restTemplate.exchange(
				customerServiceUrl,
				HttpMethod.POST,
				customerEntity,
				Customer.class
				);
		//new customer created
		 Customer createdCustomer = addCustomer.getBody();
		 
		 //create empty cart for the new customer in cart microservice
		 
		 Cart cart = new Cart();
		 cart.setCustomerId(createdCustomer.getCustomerId());
		 
		 String cartServiceUrl = "http://CART-SERVICE/api/cart";
		 ResponseEntity<Cart> cartResponse = restTemplate.postForEntity(cartServiceUrl, cart, Cart.class);
		 Cart createdCart = cartResponse.getBody();
		 
		 
		 
		 
		 
		 //save mapping b/w customer and cart in composite(cart database) microservice 
		 CustomerCartMapping mapping = new CustomerCartMapping();
		 mapping.setCartId(createdCart.getCartId());
		 mapping.setCustomerId(createdCustomer.getCustomerId());
		 
		 
		 //creating a method to add this order id in order microservice also
		// addOrderIdToOrder(orderId);
		 
		 ResponseEntity<?> response = restTemplate.exchange(
				 cartServiceUrl+"/customer-cartmap",
				 HttpMethod.POST,
				 new HttpEntity<>(mapping,headers),
				 String.class
				 );
		 
		 if(response.getStatusCode() == HttpStatus.OK) {
			 System.out.println("Customer-cart mapping saved successfully");
		 }else {
			 System.out.println("Failed to save Customer-cart mapping");
		 }
		// createdCustomer.setCart(createdCart);
		 return createdCustomer;
		
	}
	
//	private void addOrderIdToOrder(int orderId) {
//		String orderServiceUrl = "http://ORDERMICRO-SERVICE/api/order/"+orderId;
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Void> orderEntity = new HttpEntity<>(headers);
//		restTemplate.exchange(
//				orderServiceUrl,
//				HttpMethod.POST,
//				orderEntity,
//				Void.class
//				);
//		
//	}
	


}
 