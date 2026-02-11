package co.duvan.user.application.ports.input;

import co.duvan.user.domain.model.User;

public interface UpdateUserUseCase {

    User update(Long id, User user);

}
