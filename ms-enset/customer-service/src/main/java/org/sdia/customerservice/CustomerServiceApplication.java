package org.sdia.customerservice;

import lombok.ToString;
import org.sdia.customerservice.entities.Customer;
import org.sdia.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@ToString
public class CustomerServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("Abdelkarim").email("abdo@gmail.com").build(),
					Customer.builder().name("Agoujil").email("ago@gmail.com").build(),
					Customer.builder().name("sdia").email("sdia@gmail.com").build()
			));
				customerRepository.findAll().forEach(System.out::println);

		};

	}

}
