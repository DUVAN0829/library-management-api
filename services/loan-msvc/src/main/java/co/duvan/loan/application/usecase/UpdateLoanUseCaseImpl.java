package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.input.UpdateLoanUseCase;
import co.duvan.loan.application.ports.output.CopyClientPort;
import co.duvan.loan.application.ports.output.LoanEventPublisherPort;
import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.application.ports.output.UserClientPort;
import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import co.duvan.loan.domain.exceptions.LoanNotFoundException;
import co.duvan.loan.domain.model.Loan;
import co.duvan.loan.domain.model.LoanEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateLoanUseCaseImpl implements UpdateLoanUseCase {

    private final LoanRepositoryPort repositoryPort;
    private final UserClientPort userClientPort;
    private final CopyClientPort copyClientPort;
    private final LoanEventPublisherPort eventPublisherPort;

    @Override
    public LoanDetailResult update(Long id, Loan loan) {

        Loan loanUpdate = repositoryPort.findById(id)
                .map(loanDb -> {

                    loanDb.setUserId(loan.getUserId());
                    loanDb.setCopyId(loan.getCopyId());
                    loanDb.setReturnDate(loan.getReturnDate());
                    loanDb.setLoanStatus(loan.getLoanStatus());

                    return repositoryPort.save(loanDb);

                }).orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        eventPublisherPort.publish(new LoanEvent(loanUpdate.getCopyId(), "LOAN_RETURNED"));

        UserClientResponse clientResponse = userClientPort.findUserById(loan.getUserId(), null);

        CopyClientResponse copyClientResponse = copyClientPort.findCopyById(loan.getCopyId(), null);

        return new LoanDetailResult(loanUpdate, clientResponse, copyClientResponse);
    }

}