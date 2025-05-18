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

import com.wipro.SA20449357.microservicesassignment2.entities.Address;
import com.wipro.SA20449357.microservicesassignment2.services.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	@Autowired
	private AddressService addressServ;
	
	@PostMapping("/addAddress")
	public ResponseEntity<Address> addCustomerAddress(@RequestBody Address addre){
		Address address = addressServ.addCustomerAddress(addre);
		return ResponseEntity.status(HttpStatus.CREATED).body(address);
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("addressId") int addressId){
		addressServ.deleteCustomerAddress(addressId);
		return ResponseEntity.ok("deleted");
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Address> updateCustomerAddress(@RequestBody Address addre,@PathVariable int id){
		Address updateAddress = addressServ.updateCustomerAddress(addre, id);
		return ResponseEntity.ok(updateAddress);
	}
	@GetMapping("/serach/{id}")
	public ResponseEntity<Address> searchCustomerAddress(@PathVariable int id){
		Address searchCustomerAddress = addressServ.searchCustomerAddress(id);
		return ResponseEntity.ok(searchCustomerAddress);
	}

}
