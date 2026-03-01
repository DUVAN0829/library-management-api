package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.input.GetLoanUseCase;
import co.duvan.loan.application.ports.output.CopyClientPort;
import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.application.ports.output.UserClientPort;
import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import co.duvan.loan.domain.exceptions.LoanNotFoundException;
import co.duvan.loan.domain.model.Loan;
import co.duvan.loan.domain.model.LoanFilterQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetLoanUseCaseImpl implements GetLoanUseCase {

    private final LoanRepositoryPort repositoryPort;
    private final UserClientPort userClientPort;
    private final CopyClientPort copyClientPort;

    @Override
    public LoanDetailResult findById(Long id) {

        Loan loan = repositoryPort.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        UserClientResponse userResponse = userClientPort.findUserById(loan.getUserId(), null);

        CopyClientResponse copyResponse = copyClientPort.findCopyById(loan.getCopyId(), null);

        return new LoanDetailResult(loan, userResponse, copyResponse);

    }

    @Override
    public List<Loan> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public List<Loan> getWithFilters(LoanFilterQuery filter) {
        return repositoryPort.findWithFilters(filter);
    }

}