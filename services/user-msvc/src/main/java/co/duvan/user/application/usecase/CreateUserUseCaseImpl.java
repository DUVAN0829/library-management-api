package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.input.CreateUserUseCase;
import co.duvan.user.application.ports.output.IdentityProviderPort;
import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort repositoryPort;
    private final IdentityProviderPort identityProviderPort;

    @Override
    public User save(User user) {

        String keycloakId = identityProviderPort.createUser(user, "123456");

        user.setKeycloakId(keycloakId);

        return repositoryPort.save(user);
    }

}