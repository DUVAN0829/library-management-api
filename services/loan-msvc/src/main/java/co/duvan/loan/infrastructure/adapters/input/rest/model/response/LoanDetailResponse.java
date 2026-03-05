package co.duvan.loan.infrastructure.adapters.input.rest.model.response;

import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
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
        name = "Loan detail response",
        description = "Loan detail data returned by the API"
)
public class LoanDetailResponse {

    @Schema(
            description = "loanId of the loan",
            example = "3",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long loanId;

    @Schema(
            description = "loanDate of the loan",
            example = "2026-03-17",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDate loanDate;

    @Schema(
            description = "dueDate of the loan",
            example = "2026-04-02",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDate dueDate;

    @Schema(
            description = "returnDate of the loan",
            example = "2026-03-25",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private LocalDate returnDate;

    @Schema(
            description = "loanStatus of the loan",
            example = "RETURNED",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Status loanStatus;

    @Schema(description = "User who made the loan")
    private UserClientResponse user;

    @Schema(description = "Copy that was loaned")
    private CopyClientResponse copy;

}