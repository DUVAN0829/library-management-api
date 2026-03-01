package co.duvan.loan.infrastructure.adapters.output.persistence.repository;

import co.duvan.loan.domain.enums.Status;
import co.duvan.loan.infrastructure.adapters.output.persistence.entity.LoanEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends CrudRepository<LoanEntity, Long> {

    @Query("""
    SELECT l FROM LoanEntity l
    WHERE (:status IS NULL OR l.loanStatus = :status)
    AND (:loanDateFrom IS NULL OR l.loanDate >= :loanDateFrom)
    AND (:loanDateTo IS NULL OR l.loanDate <= :loanDateTo)
    AND (:dueDateFrom IS NULL OR l.dueDate >= :dueDateFrom)
    AND (:dueDateTo IS NULL OR l.dueDate <= :dueDateTo)
    AND (:returnDateFrom IS NULL OR l.returnDate >= :returnDateFrom)
    AND (:returnDateTo IS NULL OR l.returnDate <= :returnDateTo)
""")
    List<LoanEntity> findWithFilters(
            @Param("status") Status status,
            @Param("loanDateFrom") LocalDate loanDateFrom,
            @Param("loanDateTo") LocalDate loanDateTo,
            @Param("dueDateFrom") LocalDate dueDateFrom,
            @Param("dueDateTo") LocalDate dueDateTo,
            @Param("returnDateFrom") LocalDate returnDateFrom,
            @Param("returnDateTo") LocalDate returnDateTo
    );

}