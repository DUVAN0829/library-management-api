package co.duvan.loan.infrastructure.security;

import co.duvan.loan.application.ports.input.GetLoanUseCase;
import co.duvan.loan.application.ports.output.UserClientPort;
import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.domain.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanSecurity {

    private final GetLoanUseCase getLoanUseCase;
    private final UserClientPort userClientPort;

    public boolean isOwner(Authentication authentication, Long loanId) {

        String keycloakId = authentication.getName();

        Long requesterUserId = userClientPort.findUserIdByKeycloakId(keycloakId);

        LoanDetailResult loan = getLoanUseCase.findById(loanId);

        return loan.getLoan().getUserId().equals(requesterUserId);

    }

}