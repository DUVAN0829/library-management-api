package co.duvan.user.infrastructure.security;

import co.duvan.user.application.ports.input.GetUserUseCase;
import co.duvan.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurity {

    private final GetUserUseCase getUserUseCase;

    public boolean isOwner(Authentication authentication, Long userId) {

        String keycloakId = authentication.getName();

        User user = getUserUseCase.findById(userId);

        return user.getKeycloakId().equals(keycloakId);
    }

}
