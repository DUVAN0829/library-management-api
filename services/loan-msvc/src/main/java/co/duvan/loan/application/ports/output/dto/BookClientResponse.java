package co.duvan.loan.application.ports.output.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookClientResponse {

    private Long bookId;
    private String title;
    private String isbn;
    private String description;
    private String category;
    private List<String> authors;
    private String publisher;

}
