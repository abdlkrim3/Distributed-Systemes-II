package ma.sdia.comptecqrses.common_api.events;


import lombok.Getter;
import ma.sdia.comptecqrses.common_api.enums.AccountStatus;

import java.util.Date;

public class AccountActivatedEvent extends BaseEvent<String> {
    @Getter private AccountStatus status;
    @Getter private Date date;
    public AccountActivatedEvent(String id, AccountStatus status, Date date) {
        super(id);
        this.status = status;
        this.date = date;
    }

}
