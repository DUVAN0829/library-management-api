package co.duvan.prestamo.application.usecase;

import co.duvan.prestamo.application.ports.input.CreateLoanUseCase;
import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.model.Loan;
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
