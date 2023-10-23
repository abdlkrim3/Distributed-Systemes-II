package org.sdia.orderservice;

import org.sdia.orderservice.entities.Order;
import org.sdia.orderservice.entities.ProductItem;
import org.sdia.orderservice.enums.OrderStatus;
import org.sdia.orderservice.model.Customer;
import org.sdia.orderservice.model.Product;
import org.sdia.orderservice.repositories.OrderRepository;
import org.sdia.orderservice.repositories.ProductItemRepository;
import org.sdia.orderservice.service.CustomerRestClientService;
import org.sdia.orderservice.service.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderRepository orderRepository, CustomerRestClientService customerRestClientService, ProductItemRepository productItemRepository, InventoryRestClientService inventoryRestClientService){
		return args -> {
			List<Customer> customers=customerRestClientService.allCustomers().getContent().stream().toList();
			List<Product> products=inventoryRestClientService.allProducts().getContent().stream().toList();
			Long customerId=1L;
			Random rand=new Random();
			Customer customer=customerRestClientService.customerById(customerId);
			for (int i=0;i<20;i++){
				Order order=Order.builder()
						.customerId(customers.get(rand.nextInt(customers.size())).getId())
						.status(Math.random()>0.5?OrderStatus.PENDING:OrderStatus.CREATED)
						.createdAt(new Date())
						.build();
				Order savedOrder = orderRepository.save(order);
				for (int j=0;j<products.size();j++){
					if (Math.random()>0.75){
						ProductItem productItem=ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.discount(Math.random())
								.quntity(1+ rand.nextInt(10))
								.price(products.get(j).getPrice())
								.build();
						productItemRepository.save(productItem);
					}

				}
			}
		};
	}

}
