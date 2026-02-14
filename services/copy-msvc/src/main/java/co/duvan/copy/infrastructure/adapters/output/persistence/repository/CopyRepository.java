package co.duvan.copy.infrastructure.adapters.output.persistence.repository;

import co.duvan.copy.infrastructure.adapters.output.persistence.model.CopyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CopyRepository extends CrudRepository<CopyEntity, Long> {
}
