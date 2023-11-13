package ma.sdia.comptecqrses.query.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sdia.comptecqrses.common_api.enums.OperationType;
import ma.sdia.comptecqrses.common_api.events.AccountActivatedEvent;
import ma.sdia.comptecqrses.common_api.events.AccountCreatedEvent;
import ma.sdia.comptecqrses.common_api.events.AccountCreditedEvent;
import ma.sdia.comptecqrses.common_api.events.AccountDebitedEvent;
import ma.sdia.comptecqrses.common_api.queries.GetAccountByIdQuery;
import ma.sdia.comptecqrses.common_api.queries.GetAllAccountsQuery;
import ma.sdia.comptecqrses.query.entities.Account;
import ma.sdia.comptecqrses.query.entities.Operation;
import ma.sdia.comptecqrses.query.repositories.AccountRepository;
import ma.sdia.comptecqrses.query.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("*************************");
        log.info("AccountCreatedEvent received");
        Account account=new Account();
        account.setAccountId(event.getId());
        account.setCurrency(event.getCurrency());
        account.setBalance(event.getInitaialBalance());
        account.setStatus(event.getStatus());
        accountRepository.save(account);

    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("*************************");
        log.info("AccountActivatedEvent received");
        Account account=accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("*************************");
        log.info("AccountCreditedEvent received");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(event.getDate());
        operation.setType(OperationType.CREDIT);
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setBalance(account.getBalance()+event.getAmount());
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("*************************");
        log.info("AccountCreditedEvent received");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(event.getDate());
        operation.setType(OperationType.DEBIT);
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setBalance(account.getBalance()-event.getAmount());
        accountRepository.save(account);
    }
    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return accountRepository.findById(query.getId()).get();
    }
}
