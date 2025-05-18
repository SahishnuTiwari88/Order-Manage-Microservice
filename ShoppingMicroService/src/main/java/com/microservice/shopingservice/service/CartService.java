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
import com.microservice.shopingservice.entities.LineItem;

@Service
public class CartService {
	@Autowired
	private RestTemplate restTemplate;
	


	 public ResponseEntity<Void> addLineItemToCart(LineItem lineItem, int customerId) {
	       
	        String cartServiceUrl = "http://CART-SERVICE/api/cart/customer/" + customerId;
	        ResponseEntity<Cart> cartResponse = restTemplate.exchange(
	                cartServiceUrl,
	                HttpMethod.GET,
	                null,
	                Cart.class
	        );

	       
	        if (!cartResponse.getStatusCode().is2xxSuccessful() || cartResponse.getBody() == null) {
	            
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }

	       
	        int cartId = cartResponse.getBody().getCartId();

	        
	        String addItemUrl = "http://CART-SERVICE/api/cart/" + cartId + "/lineItem";

	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        
	        HttpEntity<LineItem> requestEntity = new HttpEntity<>(lineItem, headers);

	       
	        ResponseEntity<Void> responseEntity = restTemplate.exchange(
	                addItemUrl,
	                HttpMethod.PUT,
	                requestEntity,
	                Void.class
	        );

	        
	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            
	            return ResponseEntity.ok().build();
	        } else {
	            
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	}


	

