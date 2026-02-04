package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.DeleteBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.exceptions.BookNotFoundException;
import co.duvan.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
public class DeleteBookUseCaseImpl implements DeleteBookUseCase {

    private final BookRepositoryPort repositoryPort;

    @Override
    public void deleteById(Long id) {

        if (repositoryPort.findById(id).isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }

        repositoryPort.deleteById(id);

    }

}
