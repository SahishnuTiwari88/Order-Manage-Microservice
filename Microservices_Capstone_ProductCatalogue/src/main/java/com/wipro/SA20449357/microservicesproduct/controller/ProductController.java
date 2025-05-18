package com.wipro.SA20449357.microservicesproduct.controller;

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

import com.wipro.SA20449357.microservicesproduct.entities.Products;
import com.wipro.SA20449357.microservicesproduct.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Products> addProduct(@RequestBody Products products){
		Products addProduct = productService.addProduct(products);
		return ResponseEntity.status(HttpStatus.CREATED).body(addProduct);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		productService.deleteProduct(id);
		return ResponseEntity.ok("Products deleted");
	}
	@PutMapping("/{pid}")
	public ResponseEntity<Products> updateProduct(@RequestBody Products prod,
			@PathVariable("pid") int id){
		Products updateProduct = productService.updateProduct(prod, id);
		return ResponseEntity.ok(updateProduct);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Products> searchProduct(@PathVariable("productId") int productId){
	Products searchProduct = productService.searchProduct(productId);
	return ResponseEntity.ok(searchProduct);

}
}