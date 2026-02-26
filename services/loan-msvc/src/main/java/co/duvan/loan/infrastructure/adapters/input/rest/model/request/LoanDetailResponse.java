package co.duvan.loan.infrastructure.adapters.input.rest.model.request;

import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetailResponse {

    private Long loanId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String loanStatus;
    private UserClientResponse user;
    private CopyClientResponse copy;

}