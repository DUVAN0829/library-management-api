package co.duvan.user.infrastructure.adapters.output.identity;

import co.duvan.user.application.ports.output.IdentityProviderPort;
import co.duvan.user.domain.model.User;
import co.duvan.user.infrastructure.adapters.output.identity.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakIdentityAdapter implements IdentityProviderPort {

    private final KeycloakService service;

    @Override
    public String createUser(User user, String password) {
        return service.createUser(user, password);
    }

    @Override
    public void deleteUser(String keycloakId) {
        service.deleteUser(keycloakId);
    }

    @Override
    public void updateUser(String keycloakId, User user) {
        service.updateUser(keycloakId, user);
    }

}