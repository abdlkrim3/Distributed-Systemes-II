package org.sdia.customerqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.sdia.customerqueryside.entities.Customer;
import org.sdia.customerqueryside.repositories.CustomerRepository;
import org.sdia.events.CreateCustomerEvent;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class CustomerCommandHandler {
    private CustomerRepository customerRepository;
    @EventHandler
    public void on(CreateCustomerEvent event){
        log.info("***************************");
        log.info("CreateCustomerEvent recieved");
        Customer customer=new Customer();
        customer.setId(event.getId());
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());
        customerRepository.save(customer);

    }

}
