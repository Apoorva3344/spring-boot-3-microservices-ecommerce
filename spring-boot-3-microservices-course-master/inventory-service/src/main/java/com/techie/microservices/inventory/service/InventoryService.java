package com.techie.microservices.inventory.service;

import com.techie.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.techie.microservices.inventory.model.Inventory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
        log.info("Start -- Received request to check stock for skuCode: {} with quantity: {}", skuCode, quantity);

        // Check if the requested quantity is in stock
        boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
        if (!isInStock) {
            log.warn("Insufficient stock for skuCode: {}. Requested quantity: {}", skuCode, quantity);
            return false; // Return false if the requested quantity is not available
        } else {
            // Fetch the inventory item
            Optional<Inventory> optionalInventory = inventoryRepository.findBySkuCode(skuCode);
            if (optionalInventory.isEmpty()) {
                log.error("Inventory not found for skuCode: {}", skuCode);
                return false; // Return false if the inventory item is not found
            }

            // Reduce the quantity only if stock exists
            Inventory inventory = optionalInventory.get();
            int updatedQuantity = inventory.getQuantity() - quantity;
            inventory.setQuantity(updatedQuantity);

            // Save the updated inventory
            inventoryRepository.save(inventory);
            log.info("End -- Inventory reduced for skuCode: {}. New quantity: {}", skuCode, updatedQuantity);

            return true; // Return true if the operation is successful
        }
    }

    public void addInventory(String skuCode, Integer quantity) {
        log.info("Start -- Received request to add Inventory for skuCode: {} with quantity: {}", skuCode, quantity);
        var inventory = mapToInventory(skuCode, quantity);
        inventoryRepository.save(inventory);
        log.info("End -- Inventory added to Database for skuCode: {} with quantity: {}", skuCode, quantity);
    }

    private static Inventory mapToInventory(String skuCode, Integer quantity) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(quantity);
        inventory.setSkuCode(skuCode);
        return inventory;
    }
    
    public List<Inventory> getAllInventory() {
        log.info("Fetching all inventory records from database.");
        return inventoryRepository.findAll();
    }

}