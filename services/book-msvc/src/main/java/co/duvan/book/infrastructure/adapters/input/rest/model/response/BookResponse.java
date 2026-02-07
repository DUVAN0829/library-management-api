package co.duvan.book.infrastructure.adapters.input.rest.model.response;

import co.duvan.book.domain.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long bookId;
    private String title;
    private String isbn;
    private String description;
    private Category category;
    private List<String> authors;
    private String publisher;

}
