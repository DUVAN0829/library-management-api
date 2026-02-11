package co.duvan.user.application.ports.input;

import co.duvan.user.domain.model.User;

import java.util.List;

public interface GetUserUseCase {

    User findById(Long id);

    List<User> findAll();

}
