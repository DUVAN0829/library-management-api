package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.input.GetCopyUseCase;
import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import co.duvan.copy.domain.model.Copy;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetCopyUseCaseImpl implements GetCopyUseCase {

    private final CopyRepositoryPort repositoryPort;

    @Override
    public Copy findById(Long id) {

        return repositoryPort.findById(id)
                .orElseThrow(() -> new CopyNotFoundException("Copy not found with id: " + id));

    }

    @Override
    public List<Copy> findAll() {
        return repositoryPort.findAll();
    }

}
