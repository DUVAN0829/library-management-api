package co.duvan.loan.application.ports.input;

import co.duvan.loan.domain.model.Loan;

import java.util.List;

public interface GetLoanUseCase {

    Loan findById(Long id);

    List<Loan> findAll();

}
