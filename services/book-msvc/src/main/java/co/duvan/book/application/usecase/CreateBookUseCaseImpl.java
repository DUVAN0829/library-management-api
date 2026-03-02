package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.CreateBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateBookUseCaseImpl implements CreateBookUseCase {

    private final BookRepositoryPort repositoryPort;

    @Override
    public Book save(Book book) {

        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            book.setIsbn(generateIsbn());
        }

        return repositoryPort.save(book);
    }

    private String generateIsbn() {
        return "ISBN-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

}