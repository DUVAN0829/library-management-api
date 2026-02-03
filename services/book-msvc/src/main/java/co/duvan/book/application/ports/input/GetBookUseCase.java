package co.duvan.book.application.ports.input;

import co.duvan.book.domain.model.Book;

import java.util.List;

public interface GetBookUseCase {

    Book getByid(Long id);

    List<Book> findAll();

}
