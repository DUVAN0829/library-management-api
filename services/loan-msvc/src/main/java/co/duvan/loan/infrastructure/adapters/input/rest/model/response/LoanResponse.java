package co.duvan.loan.infrastructure.adapters.input.rest.model.response;

import co.duvan.loan.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Loan Response",
        description = "Loan data returned by the API"
)
public class LoanResponse {

    @Schema(
            description = "loanId of the loan",
            example = "8",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long loanId;

    @Schema(
            description = "userId of the loan",
            example = "5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long userId;

    @Schema(
            description = "copyId of the loan",
            example = "3",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
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
            example = "RETURNED",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Status loanStatus;

}
