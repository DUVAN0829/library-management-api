package co.duvan.book.application.ports.output;

import co.duvan.book.domain.model.Book;
import co.duvan.book.domain.model.BookFilterQuery;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryPort {

    Optional<Book> findById(Long id);

    Book save(Book book);

    List<Book> findAll();

    void deleteById(Long id);

    List<Book> findWithFilters(BookFilterQuery filter);

}