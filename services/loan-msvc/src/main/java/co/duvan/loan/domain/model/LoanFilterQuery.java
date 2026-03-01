package co.duvan.loan.domain.model;

import co.duvan.loan.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanFilterQuery {

    private Status status;
    private LocalDate loanDateFrom;
    private LocalDate loanDateTo;
    private LocalDate dueDateFrom;
    private LocalDate dueDateTo;
    private LocalDate returnDateFrom;
    private LocalDate returnDateTo;

}