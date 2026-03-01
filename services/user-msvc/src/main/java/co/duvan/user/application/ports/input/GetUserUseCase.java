package co.duvan.user.application.ports.input;

import co.duvan.user.domain.model.User;
import co.duvan.user.domain.model.UserFilterQuery;

import java.util.List;

public interface GetUserUseCase {

    User findById(Long id);

    List<User> findAll();

    Long findUserIdByKeycloakId(String keycloakId);

    List<User> getWithFilters(UserFilterQuery filter);

}