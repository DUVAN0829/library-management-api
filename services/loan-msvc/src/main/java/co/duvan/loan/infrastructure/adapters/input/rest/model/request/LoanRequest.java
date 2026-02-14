package co.duvan.loan.infrastructure.adapters.input.rest.model.request;

import co.duvan.loan.domain.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @NotNull(message = "Field userId cannot be null")
    private Long userId;

    @NotNull(message = "Field copyId cannot be null")
    private Long copyId;

    @NotNull(message = "Field loanDate cannot be null")
    private LocalDate loanDate;

    @NotNull(message = "Field dueDate cannot be null")
    private LocalDate dueDate;

    private LocalDate returnDate;

    @NotNull(message = "Field loanStatus cannot be null")
    private Status loanStatus;

}
