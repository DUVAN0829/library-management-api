package co.duvan.prestamo.application.ports.input;

import co.duvan.prestamo.domain.model.Loan;

import java.util.List;

public interface GetLoanUseCase {

    Loan findById(Long id);

    List<Loan> findAll();

}
