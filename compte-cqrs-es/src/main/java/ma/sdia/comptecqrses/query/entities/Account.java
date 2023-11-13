package ma.sdia.comptecqrses.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.sdia.comptecqrses.common_api.enums.AccountStatus;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;

import javax.persistence.*;
import java.util.Collection;
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    private String accountId;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;

}
