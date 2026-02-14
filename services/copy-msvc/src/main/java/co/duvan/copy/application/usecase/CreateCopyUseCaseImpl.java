package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.input.CreateCopyUseCase;
import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.model.Copy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCopyUseCaseImpl implements CreateCopyUseCase {

    private final CopyRepositoryPort repositoryPort;

    @Override
    public Copy save(Copy copy) {
        return repositoryPort.save(copy);
    }

}
