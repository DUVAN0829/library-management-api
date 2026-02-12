package co.duvan.user.infrastructure.adapters.output.persistence.repository;

import co.duvan.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
