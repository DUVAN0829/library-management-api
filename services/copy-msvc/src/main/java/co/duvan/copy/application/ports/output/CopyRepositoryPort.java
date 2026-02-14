package co.duvan.copy.application.ports.output;

import co.duvan.copy.domain.model.Copy;

import java.util.List;
import java.util.Optional;

public interface CopyRepositoryPort {

    Optional<Copy> findById(Long id);

    Copy save(Copy copy);

    List<Copy> findAll();

    void deleteById(Long id);

}
