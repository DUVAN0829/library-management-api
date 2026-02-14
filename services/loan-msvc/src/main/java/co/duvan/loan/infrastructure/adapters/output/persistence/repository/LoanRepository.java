package co.duvan.loan.infrastructure.adapters.output.persistence.repository;

import co.duvan.loan.infrastructure.adapters.output.persistence.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity, Long> {
}
