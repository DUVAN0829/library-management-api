package co.duvan.user.application.ports.output;

import co.duvan.user.domain.model.User;

public interface IdentityProviderPort {

    String createUser(User user, String password);

    void deleteUser(String keycloakId);

    void updateUser(String keycloakId, User user);

}