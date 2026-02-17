package co.duvan.copy.infrastructure.adapters.input.rest.model.response;

import co.duvan.copy.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
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
        name = "Copy Response",
        description = "Copy data returned by the API"
)
public class CopyResponse {

    @Schema(
            description = "copyId of the book",
            example = "2",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long copyId;

    @Schema(
            description = "bookId of the book",
            example = "3",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long bookId;

    @Schema(
            description = "code of the copy",
            example = "CP-12",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String code;

    @Schema(
            description = "status of the copy",
            example = "AVAILABLE",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Status status;

}