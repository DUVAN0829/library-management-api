package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.UpdateBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.exceptions.BookNotFoundException;
import co.duvan.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateBookUseCaseImpl implements UpdateBookUseCase {

    private final BookRepositoryPort bookRepositoryPort;

    @Override
    public Book update(Long id, Book book) {

        return bookRepositoryPort.findById(id)
                .map(bookDb -> {

                    bookDb.setTitle(book.getTitle());
                    bookDb.setCategory(book.getCategory());
                    bookDb.setIsbn(book.getIsbn());
                    bookDb.setAuthors(book.getAuthors());
                    bookDb.setDescription(book.getDescription());
                    bookDb.setPublisher(book.getPublisher());
                    return bookRepositoryPort.save(bookDb);

                }).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

    }

}
