package co.duvan.loan.application.ports.output;

import co.duvan.loan.domain.model.Loan;
import co.duvan.loan.domain.model.LoanFilterQuery;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {

    Optional<Loan> findById(Long id);

    Loan save(Loan loan);

    List<Loan> findAll();

    void deleteById(Long id);

    List<Loan> findWithFilters(LoanFilterQuery filter);

}