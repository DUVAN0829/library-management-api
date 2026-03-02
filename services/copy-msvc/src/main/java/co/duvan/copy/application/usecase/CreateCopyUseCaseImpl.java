package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.input.CreateCopyUseCase;
import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.enums.Status;
import co.duvan.copy.domain.model.Copy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateCopyUseCaseImpl implements CreateCopyUseCase {

    private final CopyRepositoryPort repositoryPort;

    @Override
    public Copy save(Copy copy) {

        if (copy.getCode() == null || copy.getCode().isBlank()) {
            copy.setCode(generateCode());
        }
        copy.setStatus(Status.AVAILABLE);

        return repositoryPort.save(copy);
    }

    private String generateCode() {
        return "COD-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

}