package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.CreateBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBookUseCaseImpl implements CreateBookUseCase {

    private final BookRepositoryPort repositoryPort;

    @Override
    public Book save(Book book) {
        return repositoryPort.save(book);
    }

}
