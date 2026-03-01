package co.duvan.user.infrastructure.adapters.output.persistence;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.model.User;
import co.duvan.user.domain.model.UserFilterQuery;
import co.duvan.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import co.duvan.user.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import co.duvan.user.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository repository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public User save(User user) {
        return userPersistenceMapper.toUser(repository.save(userPersistenceMapper.toUserEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(userPersistenceMapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return userPersistenceMapper.toListUser((List<UserEntity>) repository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByKeycloakId(String keycloakId) {
        return repository.findByKeycloakId(keycloakId).map(userPersistenceMapper::toUser);
    }

    @Override
    public List<User> findWithFilters(UserFilterQuery filter) {
        return repository.findWithFilters(
                        filter.getFirstname(),
                        filter.getLastname(),
                        filter.getDocumentType(),
                        filter.getDocumentNumber()
                )
                .stream()
                .map(userPersistenceMapper::toUser)
                .collect(Collectors.toList());
    }

}