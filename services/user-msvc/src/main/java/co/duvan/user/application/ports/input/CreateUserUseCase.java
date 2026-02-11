package co.duvan.user.application.ports.input;

import co.duvan.user.domain.model.User;

public interface CreateUserUseCase {

    User save(User user);

}
