package co.duvan.loan.application.ports.input;

import co.duvan.loan.domain.model.Loan;

public interface CreateLoanUseCase {

    Loan save(Loan loan);

}
