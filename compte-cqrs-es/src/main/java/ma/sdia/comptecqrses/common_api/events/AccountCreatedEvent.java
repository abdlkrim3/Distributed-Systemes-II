package ma.sdia.comptecqrses.common_api.events;


import lombok.Getter;
import ma.sdia.comptecqrses.common_api.enums.AccountStatus;

import java.util.Date;

public class AccountCreatedEvent extends BaseEvent<String> {
    @Getter private double initaialBalance;
    @Getter private String currency;
    @Getter private Date date;

    @Getter private AccountStatus status;
    public AccountCreatedEvent(String id, double initaialBalance, String currency, Date date, AccountStatus status) {
        super(id);
        this.initaialBalance = initaialBalance;
        this.currency = currency;
        this.date = date;
        this.status = status;
    }

}
