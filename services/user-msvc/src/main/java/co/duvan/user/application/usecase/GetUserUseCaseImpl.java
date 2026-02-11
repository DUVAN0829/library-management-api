package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.input.GetUserUseCase;
import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import co.duvan.user.domain.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRepositoryPort repositoryPort;

    @Override
    public User findById(Long id) {

        return repositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

    }

    @Override
    public List<User> findAll() {
        return repositoryPort.findAll();
    }

}
