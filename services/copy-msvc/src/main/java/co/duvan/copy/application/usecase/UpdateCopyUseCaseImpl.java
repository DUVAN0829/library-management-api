package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.input.UpdateCopyUseCase;
import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import co.duvan.copy.domain.model.Copy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCopyUseCaseImpl implements UpdateCopyUseCase {

    private final CopyRepositoryPort repositoryPort;

    @Override
    public Copy update(Long id, Copy copy) {
        return repositoryPort.findById(id)
                .map(copyDb -> {
                    copyDb.setBookId(copy.getBookId());
                    copyDb.setCode(copy.getCode());
                    copyDb.setStatus(copy.getStatus());
                    return repositoryPort.save(copyDb);
                }).orElseThrow(() -> new CopyNotFoundException("Copy not found with id: " + id));
    }

}
