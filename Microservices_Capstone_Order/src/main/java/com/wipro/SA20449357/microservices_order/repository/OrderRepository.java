package com.wipro.SA20449357.microservices_order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.SA20449357.microservices_order.entities.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	

	

}
