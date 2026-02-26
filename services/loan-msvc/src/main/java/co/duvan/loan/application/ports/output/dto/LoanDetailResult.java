package co.duvan.loan.application.ports.output.dto;

import co.duvan.loan.domain.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanDetailResult {

    private Loan loan;
    private UserClientResponse user;
    private CopyClientResponse copy;

}
