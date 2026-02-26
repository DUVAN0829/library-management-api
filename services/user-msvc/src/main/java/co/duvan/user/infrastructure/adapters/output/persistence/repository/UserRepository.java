package co.duvan.user.infrastructure.adapters.output.persistence.repository;

import co.duvan.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByKeycloakId(String keycloakId);

}