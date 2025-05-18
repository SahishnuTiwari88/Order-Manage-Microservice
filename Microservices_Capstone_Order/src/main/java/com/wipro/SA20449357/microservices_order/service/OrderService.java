package com.wipro.SA20449357.microservices_order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.SA20449357.microservices_order.entities.Cart;
import com.wipro.SA20449357.microservices_order.entities.LineItem;
import com.wipro.SA20449357.microservices_order.entities.Order;
import com.wipro.SA20449357.microservices_order.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private RestTemplate restTemplate;
	
	

	// add order
	public Order add(Order order) {

		return orderRepo.save(order);
	}
	
	



	//adding all int cartId, int itemId,int orderId, LineItem item  
	//working
	public Order addOrder(int cartId, int itemId,int orderId, LineItem item) {


		Order order = addOrderId(orderId);
		order.getLineItem().add(item);
		
		
		//addItemId(itemId);
		
		addCartIdToCart(cartId);
		
		addLineItemInCart(cartId,item);
		
		Order orderLineItem = getLineItemFromCart(cartId,orderId,item);
		return orderLineItem;
		//return orderRepo.save(order);
		
		
	}
	
//	private void addOrderIdtoOrder(int orderId) {
//		String orderMicroservice = "http://localhost:8087/api/order/"+orderId;
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		  HttpEntity<Order> orderEntity = new HttpEntity<>(headers);
//		restTemplate.exchange(
//				orderMicroservice,
//				HttpMethod.POST,
//				orderEntity,
//				Order.class
//				);
//		
//	}
	
	public Order addOrderId(int orderId) {
		Order order = new Order();
		order.setOrderId(orderId);;

		return orderRepo.save(order);
	}
	
//	private void addItemId(int itemId) {
//		String itemMicroService = "http://localhost:8085/api/cart/addItem/"+itemId;
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Integer> httpEntity = new HttpEntity<>(headers);
//		restTemplate.exchange(
//				itemMicroService,
//				HttpMethod.POST,
//				httpEntity,
//				LineItem.class
//				);
//	}
	
	private void addCartIdToCart(int cartId) {
		String cartMicroService = "http://CART-SERVICE/api/cart/"+cartId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Integer> httpEntity = new HttpEntity<>(headers);
		restTemplate.exchange(
				cartMicroService,
				HttpMethod.POST,
				httpEntity,
				Cart.class
				);
		
	}
	
	private void addLineItemInCart(int cartId,LineItem item) {
		String itemMicroService = "http://CART-SERVICE/api/cart/lineItem/"+cartId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<LineItem> itemEntity = new HttpEntity<>(item,headers);
		restTemplate.exchange(
				itemMicroService,
				HttpMethod.POST,
				itemEntity,
				Cart.class
				);
		
	}
	
	private Order getLineItemFromCart(int cartId,int orderId,LineItem item) {
		Order order = orderRepo.findById(orderId).orElseThrow(()->new IllegalArgumentException("Order id not found"+orderId));
		String cartMicroservice = "http://CART-SERVICE/api/cart/"+cartId;
		 HttpHeaders headers = new HttpHeaders();
	 		headers.setContentType(MediaType.APPLICATION_JSON);
	 		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
	 		ResponseEntity<Cart> lineItem = restTemplate.exchange(
	 				cartMicroservice,
	 				HttpMethod.GET,
	 				requestEntity,
	 				Cart.class
	 				);
	 		
	 		if(lineItem.getStatusCode() == HttpStatus.OK) {
	 			Cart cart = lineItem.getBody();
	 			List<LineItem> cartlineItem = cart.getLineItem();
	 			
	 			order.setOrderId(orderId);
	 			order.setLineItem(cartlineItem);
	 			
	 		}
	 		return orderRepo.save(order);
		
	}



	// delete order working

	public void deleteOrder(int cartId, int orderId) {
				// deleting cart from cart microservice
		deleteCart(cartId);
		// deleting order from order microservice
		orderDelete(orderId);

	}


	private void deleteCart(int cartId) {
		String deleteCartMicroService = "http://CART-SERVICE/api/cart/" + cartId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Void> cartEntity = new HttpEntity<>(headers);
		restTemplate.exchange(deleteCartMicroService, HttpMethod.DELETE, cartEntity, Void.class);

	}

	private void orderDelete(int orderId) {
		orderRepo.deleteById(orderId);
	}

	// update order

	public Order updateOrder(int cartId, int itemId, int orderId, LineItem item) {
		// update the lineitem with itemId in the cart microservice
		updateLineItemInCart(cartId, itemId, item);

		Order updateOrder = getOrderAndUpdateWithLineItem(cartId,orderId, item);
		
		return updateOrder;

	}
	
	

	private void updateLineItemInCart(int cartId, int itemId, LineItem item) {
		String cartMicroserviceUrl = "http://CART-SERVICE/api/cart/updateItem/" + cartId + "/" + itemId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<LineItem> requEntity = new HttpEntity<>(item, headers);
		restTemplate.exchange(cartMicroserviceUrl, HttpMethod.PUT, requEntity,
				Cart.class);
	}
	


	private Order getOrderAndUpdateWithLineItem(int cartId,int orderId, LineItem item) {
		Order order = orderRepo.findById(orderId).orElseThrow(null);
		//get updated cart with updated line Item list from cart microservice
		String orderMicro = "http://CART-SERVICE/api/cart/"+cartId;
		
		 HttpHeaders headers = new HttpHeaders();
 		headers.setContentType(MediaType.APPLICATION_JSON);
 		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

 		ResponseEntity<Cart> responseEntity = restTemplate.exchange(
 				orderMicro,
                 HttpMethod.GET,
                 requestEntity,
                 Cart.class
             );
 		//if response is successful ,update the order with updated line item list
 		if(responseEntity.getStatusCode()==HttpStatus.OK) {
 			Cart cart = responseEntity.getBody();
 			List<LineItem> lineItem = cart.getLineItem();
 			order.setOrderId(orderId);
 			order.setLineItem(lineItem);
 			
 		}
 		return orderRepo.save(order);
	}

//search order
	public Order searchOrder(int orderId,int cartId) {
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new IllegalArgumentException("Id not found"));
		

      

                // Step 4: Make a request to the cart microservice to fetch LineItems for the cartId
                String cartMicroserviceUrl = "http://CART-SERVICE/api/cart/"+cartId;
                HttpHeaders headers = new HttpHeaders();
        		headers.setContentType(MediaType.APPLICATION_JSON);
        		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        		ResponseEntity<Cart> responseEntity = restTemplate.exchange(
                        cartMicroserviceUrl,
                        HttpMethod.GET,
                        requestEntity,
                        Cart.class
                    );

                    if (responseEntity.getStatusCode() == HttpStatus.OK) {
                        Cart cartResponse = responseEntity.getBody();
                        List<LineItem> lineItems = cartResponse.getLineItem();
                        

                        // Set the fetched LineItems to the order
                       
                        order.setLineItem(lineItems);
                        return order;
                    } else {
                        // Handle the error and return an appropriate response or throw an exception
                        // For example:
                        throw new RuntimeException("Failed to retrieve LineItems from the cart microservice.");
                    }

		
 

		

	}
}

