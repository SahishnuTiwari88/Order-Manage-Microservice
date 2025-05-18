package com.wipro.SA20449357.microservicesproduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.SA20449357.microservicesproduct.entities.Products;
import com.wipro.SA20449357.microservicesproduct.repository.ProductRepository;
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	
	//add product
	public Products addProduct(Products products) {
		return productRepo.save(products);
	}
	//delete product
	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}
	
	//update product
	public Products updateProduct(Products prod,int pid) {
		Products products= productRepo.findById(pid).orElseThrow(()->
		new IllegalArgumentException("Products "+ "Products id not found"));
		products.setProductName(prod.getProductName());
		products.setProductDescription(prod.getProductDescription());
		products.setProductPrice(prod.getProductPrice());
		return productRepo.save(products);
	}
	
	//search product
	public Products searchProduct(int productid) {
		return productRepo.findById(productid).orElseThrow(()-> new IllegalArgumentException("Products Id not exist"));
		
	}
	
}
