package co.duvan.copy.application.ports.input;

import co.duvan.copy.domain.model.Copy;

public interface UpdateCopyUseCase {

    Copy update(Long id, Copy copy);

}
