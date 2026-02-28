package co.duvan.book.domain.model;

import co.duvan.book.domain.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookFilterQuery {

    private String title;

    private String author;

    private Category category;

}
