package org.sdia.orderservice.web;

import org.sdia.orderservice.entities.Order;
import org.sdia.orderservice.model.Customer;
import org.sdia.orderservice.model.Product;
import org.sdia.orderservice.repositories.OrderRepository;
import org.sdia.orderservice.repositories.ProductItemRepository;
import org.sdia.orderservice.service.CustomerRestClientService;
import org.sdia.orderservice.service.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }
@GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
        Product product=inventoryRestClientService.productById(pi.getProductId());
        pi.setProduct(product);
    });
        return order;
}
}
