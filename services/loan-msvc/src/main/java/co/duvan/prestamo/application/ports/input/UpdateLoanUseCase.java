package co.duvan.prestamo.application.ports.input;

import co.duvan.prestamo.domain.model.Loan;

public interface UpdateLoanUseCase {

    Loan update(Long id, Loan loan);

}
