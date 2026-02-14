package co.duvan.loan.application.ports.input;

import co.duvan.loan.domain.model.Loan;

public interface UpdateLoanUseCase {

    Loan update(Long id, Loan loan);

}
