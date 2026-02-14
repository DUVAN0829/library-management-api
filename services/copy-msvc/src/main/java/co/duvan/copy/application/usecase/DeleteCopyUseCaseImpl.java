package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.input.DeleteCopyUseCase;
import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCopyUseCaseImpl implements DeleteCopyUseCase {

    private final CopyRepositoryPort repositoryPort;

    @Override
    public void deleteById(Long id) {

        if (repositoryPort.findById(id).isEmpty()) {
            throw new CopyNotFoundException("Copy not found with id: " + id);
        }

        repositoryPort.deleteById(id);

    }

}
