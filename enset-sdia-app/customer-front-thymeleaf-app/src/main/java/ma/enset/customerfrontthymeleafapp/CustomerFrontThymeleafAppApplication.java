package ma.enset.customerfrontthymeleafapp;

import ma.enset.customerfrontthymeleafapp.entities.Customer;
import ma.enset.customerfrontthymeleafapp.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerFrontThymeleafAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerFrontThymeleafAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args ->{
			customerRepository.save(Customer.builder().firstName("Abdelkarim").lastName("Agoujil").email("abdlkrim@gmail.com").build());
			customerRepository.save(Customer.builder().firstName("Ahmed").lastName("faris").email("a.faris@gmail.com").build());
			customerRepository.save(Customer.builder().firstName("Mohamed").lastName("Moh").email("m.mohamed@gmail.com").build());
		};
	}

}
