package co.duvan.copy.infrastructure.adapters.input.rest.model.request;

import co.duvan.copy.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Copy Request",
        description = "Payload used to create or update a copy in the system"
)
public class CopyRequest {

    @Schema(
            description = "bookId of the copy",
            example = "3",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Positive(message = "Field bookId must be a positive number")
    @NotNull(message = "Field bookId cannot be null")
    private Long bookId;

    @Schema(
            description = "Code of the copy. If not provided, it will be generated automatically with format COD-XXXX",
            example = "COD-3F7A92BC"
    )
    @Pattern(regexp = "^COD-[a-zA-Z0-9]+$", message = "Code must have format COD-XXXX")
    private String code;

    @Schema(
            description = "Status of the copy. If not provided, defaults to AVAILABLE",
            example = "AVAILABLE"
    )
    private Status status;

}