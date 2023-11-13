package ma.sdia.comptecqrses.query.repositories;

import ma.sdia.comptecqrses.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation ,Long> {
}
