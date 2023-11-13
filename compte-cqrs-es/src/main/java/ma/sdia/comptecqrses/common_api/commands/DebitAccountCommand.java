package ma.sdia.comptecqrses.common_api.commands;

import lombok.Getter;

import java.util.Date;

public class DebitAccountCommand extends BaseCommand<String>{
    @Getter private double amount;
    @Getter private String currency;
    @Getter private Date date;
    public DebitAccountCommand(String id, double amount, String currency, Date date) {
        super(id);
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }
}
