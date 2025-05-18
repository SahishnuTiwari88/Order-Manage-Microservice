package com.wipro.SA20449357.microservices_Inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.SA20449357.microservices_Inventory.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

}
