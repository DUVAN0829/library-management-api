package co.duvan.book.application.ports.input;

import co.duvan.book.domain.model.Book;

public interface UpdateBookUseCase {

    Book update(Long id, Book book);

}
