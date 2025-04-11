package com.techie.microservices.product.client;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;

@Slf4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @PostExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    public String addInventory(@RequestParam String skuCode, @RequestParam Integer quantity);

    default String fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
        log.info("Cannot add inventory for skuCode {}, failure reason: {}", skuCode, throwable.getMessage());
        return "Fallback response: Unable to add inventory";
    }
}
