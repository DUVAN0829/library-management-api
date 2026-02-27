package co.duvan.loan.application.ports.input;

import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.domain.model.Loan;

public interface CreateLoanUseCase {

    LoanDetailResult save(Loan loan);

}