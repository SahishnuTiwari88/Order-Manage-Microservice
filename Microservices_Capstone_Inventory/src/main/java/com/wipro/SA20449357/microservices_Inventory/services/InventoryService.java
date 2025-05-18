package com.wipro.SA20449357.microservices_Inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.SA20449357.microservices_Inventory.entities.Inventory;
import com.wipro.SA20449357.microservices_Inventory.repository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;
	
	//add inventory
	public Inventory addInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}
	
	//delete inventory based on id
	public void deleteInventory(int id) {
		inventoryRepository.deleteById(id);
	}
	
	//update inventory
	public Inventory updateInventory(Inventory invent,int inventoryId) {
		Inventory updatedInventory = inventoryRepository.findById(inventoryId).orElseThrow(()->new IllegalArgumentException("inventory Id not exist"));
		updatedInventory.setProductId(invent.getProductId());
		updatedInventory.setQuantity(invent.getQuantity());
		return inventoryRepository.save(updatedInventory);
	}
	
	//search inventory
	
	public Inventory searchInventory(int id) {
		Inventory inventory = inventoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException("inventory Id not exist for search"));
		return inventory;
	}

}
