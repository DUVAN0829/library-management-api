package co.duvan.user.application.ports.output;

import co.duvan.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> save(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

}
