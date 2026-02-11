package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.input.DeleteUserUseCase;
import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort repositoryPort;

    @Override
    public void deleteById(Long id) {

        if (repositoryPort.findById(id).isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        repositoryPort.deleteById(id);

    }

}
