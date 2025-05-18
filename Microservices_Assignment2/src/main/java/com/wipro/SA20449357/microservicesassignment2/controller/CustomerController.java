package com.wipro.SA20449357.microservicesassignment2.controller;

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

import com.wipro.SA20449357.microservicesassignment2.entities.Customer;
import com.wipro.SA20449357.microservicesassignment2.services.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	private CustomerService customerServ;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		Customer addCustomer = customerServ.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(addCustomer);
	}
	
	@DeleteMapping("/remove/{custid}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("custid") int id){
		customerServ.deleteCustomer(id);
		return ResponseEntity.ok("deleted");
	}
	@PutMapping("/updateCustomer/{cid}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer cust,@PathVariable int cid){
		Customer updateCustomer = customerServ.updateCustomer(cust, cid);
		return ResponseEntity.ok(updateCustomer);
	}
	@GetMapping("/customer/{customerid}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable int customerid){
		Customer searchCustomer = customerServ.searchCustomer(customerid);
		return ResponseEntity.ok(searchCustomer);
	}
	@PostMapping("/{customerId}/{orderId}")
	public void addOrderIdToCustomer(@PathVariable int customerId,@PathVariable int orderId) {
		customerServ.addOrderIdToCustomer(customerId, orderId);
	}

}
