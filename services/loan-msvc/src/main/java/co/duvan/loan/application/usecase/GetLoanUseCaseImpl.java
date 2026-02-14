package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.input.GetLoanUseCase;
import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.domain.exceptions.LoanNotFoundException;
import co.duvan.loan.domain.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetLoanUseCaseImpl implements GetLoanUseCase {

    private final LoanRepositoryPort repositoryPort;

    @Override
    public Loan findById(Long id) {

        return repositoryPort.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
    }

    @Override
    public List<Loan> findAll() {
        return repositoryPort.findAll();
    }

}













