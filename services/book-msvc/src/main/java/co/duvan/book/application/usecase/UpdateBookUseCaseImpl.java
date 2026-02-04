package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.UpdateBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class UpdateBookUseCaseImpl implements UpdateBookUseCase {

    private final BookRepositoryPort bookRepositoryPort;

    @Override
    public Book update(Long id, Book book) {
        return null;
    }

}
