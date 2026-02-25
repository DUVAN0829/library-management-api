package co.duvan.book.infrastructure.adapters.input.rest.model.request;

import co.duvan.book.domain.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Field title cannot be blank")
    private String title;

    @Schema(
            description = "Unique identifier of the book in the system",
            example = "ISBN-97",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field isbn cannot be blank")
    private String isbn;

    @Schema(
            description = "Brief summary or description of the book",
            example = "A mystery novel set in an amusement park, written by Stephen King.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
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
    @NotEmpty(message = "Field authors cannot be empty")
    private List<String> authors;

    @Schema(
            description = "Name of the publishing company",
            example = "Hard Case Crime",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Field publisher cannot be blank")
    private String publisher;
}