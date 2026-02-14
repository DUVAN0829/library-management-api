package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.input.CreateLoanUseCase;
import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.domain.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateLoanUseCaseImpl implements CreateLoanUseCase {

    private final LoanRepositoryPort repositoryPort;

    @Override
    public Loan save(Loan loan) {
        return repositoryPort.save(loan);
    }

}
