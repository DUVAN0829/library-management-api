package co.duvan.prestamo.application.usecase;

import co.duvan.prestamo.application.ports.input.GetLoanUseCase;
import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.exceptions.LoanNotFoundException;
import co.duvan.prestamo.domain.model.Loan;
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













