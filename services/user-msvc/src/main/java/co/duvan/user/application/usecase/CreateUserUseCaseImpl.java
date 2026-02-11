package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.input.CreateUserUseCase;
import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import co.duvan.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort repositoryPort;

    @Override
    public User save(User user) {
        return repositoryPort.save(user);
    }

}
