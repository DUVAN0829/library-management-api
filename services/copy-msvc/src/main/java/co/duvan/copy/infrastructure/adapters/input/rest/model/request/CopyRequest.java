package co.duvan.copy.infrastructure.adapters.input.rest.model.request;

import co.duvan.copy.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Field bookId cannot be null")
    private Long bookId;

    @Schema(
            description = "code of the copy",
            example = "CP-12",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field code cannot be blank")
    private String code;

    @Schema(
            description = "status of the copy",
            example = "AVAILABLE",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field status cannot be null")
    private Status status;

}