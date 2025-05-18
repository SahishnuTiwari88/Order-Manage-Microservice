package com.wipro.SA20449357.microservices_Inventory.controller;

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

import com.wipro.SA20449357.microservices_Inventory.entities.Inventory;
import com.wipro.SA20449357.microservices_Inventory.services.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory){
		Inventory addInventory = inventoryService.addInventory(inventory);
		return ResponseEntity.status(HttpStatus.CREATED).body(addInventory);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteInventory(@PathVariable int id){
		inventoryService.deleteInventory(id);
		return ResponseEntity.ok("Inventory deleted");
	}
	@PutMapping("/update/{inventoryId}")
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory invent,
			@PathVariable int inventoryId){
		Inventory updateInventory = inventoryService.updateInventory(invent, inventoryId);
		return ResponseEntity.ok(updateInventory);
	}
	@GetMapping("/get/{inventId}")
	public ResponseEntity<Inventory> searchInventory(@PathVariable int inventId){
		Inventory searchInventory = inventoryService.searchInventory(inventId);
		return ResponseEntity.ok(searchInventory);
	}
	

}
