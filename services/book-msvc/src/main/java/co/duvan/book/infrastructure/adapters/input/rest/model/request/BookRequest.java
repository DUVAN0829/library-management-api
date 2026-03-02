package co.duvan.book.infrastructure.adapters.input.rest.model.request;

import co.duvan.book.domain.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Book  Request",
        description = "Payload used to create or update a book in the system"
)
public class BookRequest {

    @Schema(
            description = "Title of the book",
            example = "Joyland",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(min = 1, max = 255, message = "Field title must be between 1 and 255 characters")
    @NotBlank(message = "Field title cannot be blank")
    private String title;

    @Schema(
            description = "Unique identifier of the book. If not provided, it will be generated automatically with format ISBN-XXXX",
            example = "ISBN-97f3a2bc"
    )
    @Pattern(regexp = "^ISBN-[a-zA-Z0-9]+$", message = "ISBN must have format ISBN-XXXX")
    private String isbn;

    @Schema(
            description = "Brief summary or description of the book",
            example = "A mystery novel set in an amusement park, written by Stephen King.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(max = 1000, message = "Field description cannot exceed 1000 characters")
    @NotBlank(message = "Field description cannot be blank")
    private String description;

    @Schema(
            description = "Category to which the book belongs",
            example = "THRILLER",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Field category cannot be null")
    private Category category;

    @Schema(
            description = "List of authors of the book",
            example = "[\"Stephen King\"]",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(max = 10, message = "A book cannot have more than 10 authors")
    @NotEmpty(message = "Field authors cannot be empty")
    private List<String> authors;

    @Schema(
            description = "Name of the publishing company",
            example = "Hard Case Crime",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(max = 255, message = "Field publisher cannot exceed 255 characters")
    @NotBlank(message = "Field publisher cannot be blank")
    private String publisher;
}