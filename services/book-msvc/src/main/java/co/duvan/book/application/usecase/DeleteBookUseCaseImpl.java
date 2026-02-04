package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.input.DeleteBookUseCase;
import co.duvan.book.application.ports.output.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class DeleteBookUseCaseImpl implements DeleteBookUseCase {

    private final BookRepositoryPort repositoryPort;

    @Override
    public void deleteById(Long id) {

    }

}
