package co.duvan.book.application.ports.input;

import co.duvan.book.domain.model.Book;

public interface CreateBookUseCase {

    Book save(Book book);

}
