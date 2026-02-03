package co.duvan.book.domain.model;

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
public class Book {

    private Long bookId;
    private String title;
    private String isbn;
    private String description;
    private Category category;
    private List<String> authors;
    private String publisher;

}
