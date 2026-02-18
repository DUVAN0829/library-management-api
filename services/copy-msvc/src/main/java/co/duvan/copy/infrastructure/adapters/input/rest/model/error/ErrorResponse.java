package co.duvan.copy.infrastructure.adapters.input.rest.model.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Schema(
        name = "Error Response",
        description = "Standard error response returned by the API"
)
public class ErrorResponse {

    @Schema(example = "ERR_COPY_01")
    private String code;

    @Schema(example = "Copy not found")
    private String message;

    @Schema(example = "[]")
    private List<String> details;

    @Schema(example = "2026-02-17T10:16:29")
    private LocalDateTime timestamp;

}