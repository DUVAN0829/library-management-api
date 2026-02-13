package co.duvan.prestamo.application.ports.output;

import co.duvan.prestamo.domain.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {

    Optional<Loan> findById(Long id);

    Loan save(Loan loan);

    List<Loan> findAll();

    void deleteById(Long id);

}
