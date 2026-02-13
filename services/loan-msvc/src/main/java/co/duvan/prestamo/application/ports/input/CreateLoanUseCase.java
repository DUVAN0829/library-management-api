package co.duvan.prestamo.application.ports.input;

import co.duvan.prestamo.domain.model.Loan;

public interface CreateLoanUseCase {

    Loan save(Loan loan);

}
