package co.duvan.prestamo.application.usecase;

import co.duvan.prestamo.application.ports.input.UpdateLoanUseCase;
import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.exceptions.LoanNotFoundException;
import co.duvan.prestamo.domain.model.Loan;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateLoanUseCaseImpl implements UpdateLoanUseCase {

    private final LoanRepositoryPort repositoryPort;

    @Override
    public Loan update(Long id, Loan loan) {

        return repositoryPort.findById(id)
                .map(loanDb -> {

                    loanDb.setUserId(loan.getUserId());
                    loanDb.setCopyId(loan.getCopyId());
                    loanDb.setLoanDate(loan.getLoanDate());
                    loanDb.setDueDate(loan.getDueDate());
                    loanDb.setReturnDate(loan.getReturnDate());
                    loanDb.setLoanStatus(loan.getLoanStatus());

                    return repositoryPort.save(loanDb);

                }).orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
    }

}
