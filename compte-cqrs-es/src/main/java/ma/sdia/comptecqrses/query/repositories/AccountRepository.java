package ma.sdia.comptecqrses.query.repositories;

import ma.sdia.comptecqrses.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
