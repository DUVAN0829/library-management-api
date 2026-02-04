package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.GetBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
public class GetBookUseCaseImpl implements GetBookUseCase {

    private final BookRepositoryPort repositoryPort;

    @Override
    public Book getByid(Long id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

}


