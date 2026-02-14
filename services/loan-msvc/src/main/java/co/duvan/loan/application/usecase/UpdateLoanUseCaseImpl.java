package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.input.UpdateLoanUseCase;
import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.domain.exceptions.LoanNotFoundException;
import co.duvan.loan.domain.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
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
