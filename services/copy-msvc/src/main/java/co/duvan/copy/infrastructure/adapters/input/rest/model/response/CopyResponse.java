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
            description = "copyId of the copy",
            example = "2"
    )
    private Long copyId;

    @Schema(
            description = "bookId of the copy",
            example = "3"
    )
    private Long bookId;

    @Schema(
            description = "code of the copy",
            example = "COD-3F7A92BC"
    )
    private String code;

    @Schema(
            description = "status of the copy",
            example = "AVAILABLE"
    )
    private Status status;

}