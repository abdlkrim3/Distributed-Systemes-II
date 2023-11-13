package ma.sdia.comptecqrses.query.contrllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sdia.comptecqrses.common_api.queries.GetAccountByIdQuery;
import ma.sdia.comptecqrses.common_api.queries.GetAllAccountsQuery;
import ma.sdia.comptecqrses.query.entities.Account;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/accounts")
@AllArgsConstructor
@Slf4j
public class AccountQueryController {
    private QueryGateway queryGateway;
    @GetMapping("/allAccounts")
    private List<Account> accountList(){
        List<Account> response = queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
        return response;
    }

    @GetMapping("/byId/{id}")
    private Account getAccount(@PathVariable String id){
        Account account = queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).join();
        return account;
    }
}
