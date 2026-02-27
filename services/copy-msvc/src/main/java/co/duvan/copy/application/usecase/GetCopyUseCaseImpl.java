package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.input.GetCopyUseCase;
import co.duvan.copy.application.ports.output.BookClientPort;
import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.application.ports.output.dto.BookClientResponse;
import co.duvan.copy.application.ports.output.dto.CopyDetailResult;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import co.duvan.copy.domain.model.Copy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCopyUseCaseImpl implements GetCopyUseCase {

    private final CopyRepositoryPort repositoryPort;
    private final BookClientPort bookClientPort;

    @Override
    public CopyDetailResult findById(Long id) {

        Copy copy = repositoryPort.findById(id)
                .orElseThrow(() -> new CopyNotFoundException("Copy not found with id: " + id));

        BookClientResponse book = bookClientPort.findBookById(copy.getBookId(), null);

        return new CopyDetailResult(copy, book);

    }

    @Override
    public List<Copy> findAll() {
        return repositoryPort.findAll();
    }

}