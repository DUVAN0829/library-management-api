package co.duvan.copy.infrastructure.adapters.output.persistence;

import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.model.Copy;
import co.duvan.copy.infrastructure.adapters.output.persistence.mapper.CopyPersistenceMapper;
import co.duvan.copy.infrastructure.adapters.output.persistence.model.CopyEntity;
import co.duvan.copy.infrastructure.adapters.output.persistence.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CopyPersistenceAdapter implements CopyRepositoryPort {

    private final CopyPersistenceMapper copyPersistenceMapper;
    private final CopyRepository repository;

    @Override
    public Optional<Copy> findById(Long id) {
        return repository.findById(id).map(copyPersistenceMapper::toCopy);
    }

    @Override
    public Copy save(Copy copy) {
        return copyPersistenceMapper.toCopy(repository.save(copyPersistenceMapper.toCopyEntity(copy)));
    }

    @Override
    public List<Copy> findAll() {
        return copyPersistenceMapper.toCopyList((List<CopyEntity>) repository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
