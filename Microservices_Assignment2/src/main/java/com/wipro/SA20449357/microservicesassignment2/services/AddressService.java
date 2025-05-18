package com.wipro.SA20449357.microservicesassignment2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.SA20449357.microservicesassignment2.entities.Address;
import com.wipro.SA20449357.microservicesassignment2.repository.AddressRepository;
@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	//adding customer address
	public Address addCustomerAddress(Address address) {
		return addressRepository.save(address);
	}
	
	//delete address
	public void deleteCustomerAddress(int addressId) {
		addressRepository.deleteById(addressId);
	}
	
	//update address
	public Address updateCustomerAddress(Address addre,int addressId) {
		Address address = addressRepository.findById(addressId).orElseThrow(()->new IllegalArgumentException("Address "
				+ "id not exist"));
		address.setDoorNo(addre.getDoorNo());
		address.setStreetName(addre.getStreetName());
		address.setLayout(addre.getLayout());
		address.setCity(addre.getCity());
		address.setPincode(addre.getPincode());
		return addressRepository.save(address);
		
	}
	//search address
	public Address searchCustomerAddress(int addressId) {
		return addressRepository.findById(addressId).orElseThrow(()->new IllegalArgumentException("Address "
				+ "id not exist"));
	}

}
