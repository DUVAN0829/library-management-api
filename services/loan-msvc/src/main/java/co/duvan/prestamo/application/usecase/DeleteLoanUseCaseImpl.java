package co.duvan.prestamo.application.usecase;

import co.duvan.prestamo.application.ports.input.DeleteLoanUseCase;
import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.exceptions.LoanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteLoanUseCaseImpl implements DeleteLoanUseCase {

    private final LoanRepositoryPort repositoryPort;

    @Override
    public void deleteById(Long id) {

        if (repositoryPort.findById(id).isEmpty()) {
            throw new LoanNotFoundException("Loand not found with id: " + id);
        }

        repositoryPort.deleteById(id);

    }

}
