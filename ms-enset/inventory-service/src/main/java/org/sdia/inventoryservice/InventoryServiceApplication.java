package org.sdia.inventoryservice;

import org.sdia.inventoryservice.entities.Product;
import org.sdia.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.saveAll(List.of(
					Product.builder().name("Laptope").price(3000).quantity(6).build(),
					Product.builder().name("Smartphone").price(1200).quantity(16).build(),
					Product.builder().name("SmartWatch").price(800).quantity(34).build(),
					Product.builder().name("Airpod").price(300).quantity(42).build()
			));
		};
	}

}
