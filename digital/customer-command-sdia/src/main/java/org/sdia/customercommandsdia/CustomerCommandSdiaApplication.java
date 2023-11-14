package org.sdia.customercommandsdia;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerCommandSdiaApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(CustomerCommandSdiaApplication.class, args);
    }
    @Bean
    public CommandBus commandBus(){
        return SimpleCommandBus.builder().build();
    }

}
