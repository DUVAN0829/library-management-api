package co.duvan.user.application.ports.output;

import co.duvan.user.domain.model.User;
import co.duvan.user.domain.model.UserFilterQuery;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    Optional<User> findByKeycloakId(String keycloakId);

    List<User> findWithFilters(UserFilterQuery filter);

}