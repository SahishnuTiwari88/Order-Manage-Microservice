package com.wipro.SA20449357.microservices_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.SA20449357.microservices_cart.entities.LineItem;
@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Integer> {

}
