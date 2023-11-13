package ma.sdia.comptecqrses.commands.controllers;

import lombok.AllArgsConstructor;
import ma.sdia.comptecqrses.common_api.commands.CreatAccountCommand;
import ma.sdia.comptecqrses.common_api.commands.CreditAccountCommand;
import ma.sdia.comptecqrses.common_api.commands.DebitAccountCommand;
import ma.sdia.comptecqrses.common_api.dtos.CreatAccountRequestDTO;
import ma.sdia.comptecqrses.common_api.dtos.CreditAccountRequestDTO;
import ma.sdia.comptecqrses.common_api.dtos.DebiteAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommantController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping("/create")
    public CompletableFuture<String> createAccount (@RequestBody CreatAccountRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreatAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency(),
                new Date()));
        return commandResponse;
    }
    @PutMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreditAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        ));
        return commandResponse;
    }
    @PutMapping("/debit")
    public CompletableFuture<String> creditAccount(@RequestBody DebiteAccountRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new DebitAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency(),
                new Date()));
        return commandResponse;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exeptionHandler(Exception exception){
        ResponseEntity<String> responseEntity=new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }
    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }
}
