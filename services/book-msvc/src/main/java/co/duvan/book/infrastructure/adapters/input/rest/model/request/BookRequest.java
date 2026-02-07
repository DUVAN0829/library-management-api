package co.duvan.book.infrastructure.adapters.input.rest.model.request;

import co.duvan.book.domain.enums.Category;
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
public class BookRequest {

    @NotBlank(message = "Field title cannot be blank")
    private String title;

    @NotBlank(message = "Field isbn cannot be blank")
    private String isbn;

    @NotBlank(message = "Field description cannot be blank")
    private String description;

    @NotNull(message = "Field category cannot be null")
    private Category category;

    @NotEmpty(message = "Field authors cannot be null")
    private List<String> authors;

    @NotBlank(message = "Field publisher cannot be blank")
    private String publisher;

}
