package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.input.CreateLoanUseCase;
import co.duvan.loan.application.ports.output.CopyClientPort;
import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.application.ports.output.UserClientPort;
import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import co.duvan.loan.domain.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateLoanUseCaseImpl implements CreateLoanUseCase {

    private final LoanRepositoryPort repositoryPort;
    private final UserClientPort userClientPort;
    private final CopyClientPort copyClientPort;

    @Override
    public LoanDetailResult save(Loan loan) {

        Loan loanDb = repositoryPort.save(loan);

        UserClientResponse userResponse = userClientPort.findUserById(loanDb.getUserId(), null);

        CopyClientResponse copyResponse = copyClientPort.findCopyById(loanDb.getCopyId(), null);

        return new LoanDetailResult(loanDb, userResponse, copyResponse);

    }

}