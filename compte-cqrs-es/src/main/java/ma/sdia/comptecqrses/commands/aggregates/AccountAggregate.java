package ma.sdia.comptecqrses.commands.aggregates;

import ma.sdia.comptecqrses.common_api.commands.CreatAccountCommand;
import ma.sdia.comptecqrses.common_api.commands.CreditAccountCommand;
import ma.sdia.comptecqrses.common_api.commands.DebitAccountCommand;
import ma.sdia.comptecqrses.common_api.enums.AccountStatus;
import ma.sdia.comptecqrses.common_api.events.AccountActivatedEvent;
import ma.sdia.comptecqrses.common_api.events.AccountCreatedEvent;
import ma.sdia.comptecqrses.common_api.events.AccountCreditedEvent;
import ma.sdia.comptecqrses.common_api.events.AccountDebitedEvent;
import ma.sdia.comptecqrses.common_api.exceptions.AmountNegativeException;
import ma.sdia.comptecqrses.common_api.exceptions.BallanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
        // required by AXON
    }
    @CommandHandler
    public AccountAggregate(CreatAccountCommand creatAccountCommand) {
        if (creatAccountCommand.getInitialBalance() <0) throw new RuntimeException("Negative initial balance no allowed");

        AggregateLifecycle.apply(new AccountCreatedEvent(
               creatAccountCommand.getId(),
               creatAccountCommand.getInitialBalance(),
               creatAccountCommand.getCurrency(),
                new Date(), AccountStatus.CREATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId=event.getId();
        this.balance=event.getInitaialBalance();
        this.currency =event.getCurrency();
        this.status=AccountStatus.CREATED;

        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED,
                new Date()));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status=event.getStatus();
    }

    @CommandHandler
    public void handle (CreditAccountCommand credit){
        if(credit.getAmount()<0)throw new AmountNegativeException("Amount should not be negative");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                credit.getId(),
                credit.getAmount(),
                credit.getCurrency(),
                new Date()));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance+=event.getAmount();
    }

    @CommandHandler
    public void handle (DebitAccountCommand debite){
        if(debite.getAmount()<0)throw new AmountNegativeException("Amount should not be negative");
        if(this.balance<debite.getAmount()) throw new BallanceNotSufficientException("Ballance not sufficient => "+balance);
        AggregateLifecycle.apply(new AccountDebitedEvent(
                debite.getId(),
                debite.getAmount(),
                debite.getCurrency(),
                debite.getDate()));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-=event.getAmount();
    }
}
