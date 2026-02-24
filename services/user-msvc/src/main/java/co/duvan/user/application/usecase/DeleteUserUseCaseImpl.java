package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.input.DeleteUserUseCase;
import co.duvan.user.application.ports.output.IdentityProviderPort;
import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import co.duvan.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort repositoryPort;
    private final IdentityProviderPort providerPort;

    @Override
    public void deleteById(Long id) {

        User user = repositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));

        providerPort.deleteUser(user.getKeycloakId());

        repositoryPort.deleteById(id);

    }

}