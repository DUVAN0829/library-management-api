package co.duvan.loan.application.ports.input;

import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.domain.model.Loan;

import java.util.List;

public interface GetLoanUseCase {

    LoanDetailResult findById(Long id);

    List<Loan> findAll();

}