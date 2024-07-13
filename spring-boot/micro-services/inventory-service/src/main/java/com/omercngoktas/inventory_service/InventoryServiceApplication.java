package com.omercngoktas.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.omercngoktas.inventory_service.model.Inventory;
import com.omercngoktas.inventory_service.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
	// return args -> {
	// Inventory inventory = new Inventory("iphone_13", 100);
	// Inventory inventory2 = new Inventory("iphone_13_red", 0);
	// inventoryRepository.save(inventory);
	// inventoryRepository.save(inventory2);
	// };
	// }

}
