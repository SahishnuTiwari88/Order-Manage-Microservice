package com.wipro.SA20449357.microservicesassignment2.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.SA20449357.microservicesassignment2.entities.Customer;
import com.wipro.SA20449357.microservicesassignment2.repository.CustomerRepository;
@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	//adding customer
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	//deleting customer
	public void deleteCustomer(int custId) {
		customerRepository.deleteById(custId);
	}
	
	//adding order id to customer
	
	public void addOrderIdToCustomer(int customerId,int OrderId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(null);
		customer.setOrderId(OrderId);
		customerRepository.save(customer);
	}
	
	//update customer
	
	public Customer updateCustomer(Customer custom, int custId) {
		Customer customer = customerRepository.findById(custId).orElseThrow(() -> new IllegalArgumentException("customer id not found"));
		customer.setCustomerName(custom.getCustomerName());
		customer.setCustomerEmail(custom.getCustomerEmail());
		return customerRepository.save(customer);
		
	}
	
	//search customer based on id
	public Customer searchCustomer(int custId) {
		return customerRepository.findById(custId).orElseThrow(() -> new IllegalArgumentException("Customer id not found"));
	}

}
