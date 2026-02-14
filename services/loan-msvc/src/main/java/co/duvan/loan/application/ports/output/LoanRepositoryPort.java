package co.duvan.loan.application.ports.output;

import co.duvan.loan.domain.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {

    Optional<Loan> findById(Long id);

    Loan save(Loan loan);

    List<Loan> findAll();

    void deleteById(Long id);

}
