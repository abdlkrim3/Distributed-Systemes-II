package org.sdia.customerqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.sdia.customerqueryside.entities.Customer;
import org.sdia.customerqueryside.repositories.CustomerRepository;
import org.sdia.queries.GetAllCustomersQuery;
import org.sdia.queries.GetCustomerById;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerQueryHandler {
    private CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> customers(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }
    @QueryHandler
    public Customer customer(GetCustomerById query){
        return customerRepository.findById(query.getId())
                .orElseThrow(()->new RuntimeException(("Customer not found")));
    }
}
