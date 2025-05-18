package com.wipro.SA20449357.microservicesproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.SA20449357.microservicesproduct.entities.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{

}
