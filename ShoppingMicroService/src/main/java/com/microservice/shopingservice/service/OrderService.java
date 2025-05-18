package com.microservice.shopingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.shopingservice.entities.Cart;
import com.microservice.shopingservice.entities.Customer;
import com.microservice.shopingservice.entities.Order;

@Service
public class OrderService {
	@Autowired
	private RestTemplate restTemplate;
	
	public void findCustomer(int customerId,int orderId) {
		String orderServiceUrl = "http://CUSTOMER-SERVICE/api/customer/"+customerId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> customerEntity = new HttpEntity<>(headers);
		ResponseEntity<Customer> responseEntity = restTemplate.exchange(
				orderServiceUrl,
				HttpMethod.GET,
				customerEntity,
				Customer.class
				);
		 int customerId2 = responseEntity.getBody().getCustomerId();
		 System.out.println(customerId2);
		 addOrderIdToCustomer(customerId,orderId);
		 addOrderIdToOrder(orderId);
		 
		
		
	}
	
	public void addOrderIdToCustomer(int customerId,int orderId) {
		String orderServiceUrl = "http://CUSTOMER-SERVICE/api/"+customerId+ "/"+orderId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> customerEntity = new HttpEntity<>(headers);
		 restTemplate.exchange(
				orderServiceUrl,
				HttpMethod.POST,
				customerEntity,
				Customer.class
				);

	}
	
	public void addOrderIdToOrder(int orderId) {
		String orderServiceUrl = "http://ORDERMICRO-SERVICE/api/order/"+orderId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> customerEntity = new HttpEntity<>(headers);
		 restTemplate.exchange(
				orderServiceUrl,
				HttpMethod.POST,
				customerEntity,
				Customer.class
				);

	}
	
	//get customer order detail

	
	public Customer getCustomerOrderDetail(int customerId) {
		
        Customer cust = new Customer();
        // Fetch customer details from the Customer Microservice
        String customerServiceUrl = "http://CUSTOMER-SERVICE/api/customer/" + customerId;
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> customerEntity = new HttpEntity<>(headers);
        ResponseEntity<Customer> customerResponseEntity = restTemplate.exchange(
                customerServiceUrl,
                HttpMethod.GET,
                customerEntity,
                Customer.class
        );

      
         Customer customer = customerResponseEntity.getBody();
         cust.setCustomerId(customerId);
         cust.setCustomerName(customer.getCustomerName());
         cust.setCustomerEmail(customer.getCustomerEmail());
         cust.setCustomerBillingAddress(customer.getCustomerBillingAddress());
         cust.setCustomerShippingAddress(customer.getCustomerShippingAddress());
         
         
        // int cartId = customer.getCartId();
         //getting order id
         int orderId = customer.getOrderId();
         System.out.println("Order id :"+orderId);
        // return customer;
         cust.setOrderId(orderId);
         
         //get cart id from cart microservice
         
         String cartServiceUrl = "http://CART-SERVICE/api/cart/customer/" + customerId;
         HttpHeaders cartheaders = new HttpHeaders();
         cartheaders.setContentType(MediaType.APPLICATION_JSON);
 		HttpEntity<Cart> cartEntity = new HttpEntity<>(cartheaders);
         ResponseEntity<Cart> cartResponseEntity = restTemplate.exchange(
        		 cartServiceUrl,
                 HttpMethod.GET,
                 cartEntity,
                 Cart.class
         );
         
         Cart cart = cartResponseEntity.getBody();
         //if(cart!=null) {
         int cartId = cart.getCartId();
         System.out.println("Cart id :"+cartId);
         cust.setCartId(cartId);
         
         
         //fetch line item for order id and cart id from order service
         
         String orderServiceUrl = "http://ORDERMICRO-SERVICE/api/order/getdata/" + orderId+ "/"+cartId;
         HttpHeaders orderheaders = new HttpHeaders();
         orderheaders.setContentType(MediaType.APPLICATION_JSON);
 		HttpEntity<Order> orderEntity = new HttpEntity<>(orderheaders);
         ResponseEntity<Order> orderResponseEntity = restTemplate.exchange(
        		 orderServiceUrl,
                 HttpMethod.GET,
                 orderEntity,
                 Order.class
         );
         
         Order order = orderResponseEntity.getBody();
      System.out.println(order.getLineItem());
         cust.setLineItem(order.getLineItem());
         return cust;

       

}
}

