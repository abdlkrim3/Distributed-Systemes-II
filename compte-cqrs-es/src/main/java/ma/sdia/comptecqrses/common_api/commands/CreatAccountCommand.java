package ma.sdia.comptecqrses.common_api.commands;

import lombok.Getter;

import java.util.Date;

public class CreatAccountCommand extends BaseCommand<String>{
    @Getter private double initialBalance;
    @Getter private String currency;
    @Getter private Date date;
    public CreatAccountCommand(String id, double initialBalance, String currency, Date date) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.date = date;
    }
}
