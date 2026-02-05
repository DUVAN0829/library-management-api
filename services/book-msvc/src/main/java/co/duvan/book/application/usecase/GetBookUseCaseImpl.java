package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.GetBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.exceptions.BookNotFoundException;
import co.duvan.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetBookUseCaseImpl implements GetBookUseCase {

    private final BookRepositoryPort repositoryPort;

    @Override
    public Book findByid(Long id) {

        return repositoryPort.findById(id).
                orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

    }

    @Override
    public List<Book> findAll() {

        return repositoryPort.findAll();

    }

}


