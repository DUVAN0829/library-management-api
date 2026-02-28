package co.duvan.book.application.ports.input;

import co.duvan.book.domain.model.Book;
import co.duvan.book.domain.model.BookFilterQuery;

import java.util.List;

public interface GetBookUseCase {

    Book findById(Long id);

    List<Book> findAll();

    List<Book> getWithFilters(BookFilterQuery filter);

}