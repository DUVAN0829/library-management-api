package co.duvan.user.infrastructure.adapters.output.persistence;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.model.User;
import co.duvan.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import co.duvan.user.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import co.duvan.user.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository repository;
    private final UserPersistenceMapper persistenceMapper;

    @Override
    public User save(User user) {
        return persistenceMapper.toUser(repository.save(persistenceMapper.toUserEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(persistenceMapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return persistenceMapper.toListUser((List<UserEntity>) repository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}

















