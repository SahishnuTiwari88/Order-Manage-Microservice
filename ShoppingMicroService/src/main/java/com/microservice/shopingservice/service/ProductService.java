package com.microservice.shopingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.shopingservice.entities.Product;

@Service
public class ProductService {

	@Autowired
	private RestTemplate restTemplate;
	
	
	public Product createProduct(Product product) {
		String productsendUrl = "http://PRODUCTCATALOGUE-SERVICE/api/products";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Product> httpEntity = new HttpEntity<>(product,headers);
		ResponseEntity<Product> addProduct = restTemplate.exchange(
				productsendUrl,
				HttpMethod.POST,
				httpEntity,
				Product.class
				);
		return addProduct.getBody();
	}
	
	public Product getProductFromProductService(int productId) {
		String productserviceUrl = "http://PRODUCTCATALOGUE-SERVICE/api/products/"+productId;
		return restTemplate.getForObject(productserviceUrl, Product.class);
	}
	

}
