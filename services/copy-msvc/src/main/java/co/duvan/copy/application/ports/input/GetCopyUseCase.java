package co.duvan.copy.application.ports.input;

import co.duvan.copy.domain.model.Copy;

import java.util.List;

public interface GetCopyUseCase {

    Copy findById(Long id);

    List<Copy> findAll();

}
