package ma.sdia.comptecqrses.common_api.events;


import lombok.Getter;

import java.util.Date;

public class AccountDebitedEvent extends BaseEvent<String> {
    @Getter private double amount;
    @Getter private String currency;
    @Getter private Date date;
    public AccountDebitedEvent(String id, double amount, String currency, Date date) {
        super(id);
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

}
