package com.techie.microservices.inventory.controller;

import com.techie.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.techie.microservices.inventory.model.Inventory;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addInventory(@RequestParam String skuCode, @RequestParam Integer quantity) {
        // Calls the order service to place the order
    	inventoryService.addInventory(skuCode, quantity);       
        // Returns a success message
        return "Inventory added Successfully";
    }
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

}
