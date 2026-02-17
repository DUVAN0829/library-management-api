package co.duvan.book.infrastructure.adapters.input.rest.model.error;

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

    @Schema(example = "BOOK-404")
    private String code;

    @Schema(example = "Book not found")
    private String message;

    @Schema(example = "[\"Field title cannot be blank\"]")
    private List<String> details;

    @Schema(example = "2026-02-17T10:15:30")
    private LocalDateTime timestamp;

}