package com.wipro.SA20449357.microservicesassignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.SA20449357.microservicesassignment2.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
