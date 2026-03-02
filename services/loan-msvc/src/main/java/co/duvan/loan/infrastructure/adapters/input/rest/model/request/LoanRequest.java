package co.duvan.loan.infrastructure.adapters.input.rest.model.request;

import co.duvan.loan.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name = "Loan Request",
        description = "Payload used to create or update a loan in the system"
)
public class LoanRequest {

    @Schema(
            description = "userId of the loan",
            example = "5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field userId cannot be null")
    private Long userId;

    @Schema(
            description = "copyId of the loan",
            example = "3",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field copyId cannot be null")
    private Long copyId;

    @Schema(
            description = "loanDate of the loan",
            example = "2026-02-17",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDate loanDate;

    @Schema(
            description = "dueDate of the loan",
            example = "2026-03-03",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDate dueDate;

    @Schema(
            description = "returnDate of the loan",
            example = "2026-02-25",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private LocalDate returnDate;

    @Schema(
            description = "loanStatus of the loan",
            example = "ACTIVE",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field loanStatus cannot be null")
    private Status loanStatus;

}