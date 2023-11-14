package org.sdia.customercommandsdia.aggregats;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sdia.commands.CreateCustomerCommand;
import org.sdia.events.CreateCustomerEvent;

@Aggregate
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String customerId;
    private String name;
    private String email;

    public CustomerAggregate() {
    }
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new CreateCustomerEvent(
                command.getId(),
                command.getName(),
                command.getEmail()
        ));

    }
    @EventSourcingHandler
    public void on(CreateCustomerEvent event){
        log.info("******************************");
        log.info("CreateCustomerEvent received");
        this.customerId= event.getId();
        this.email=event.getEmail();
        this.name= event.getName();
    }
}
