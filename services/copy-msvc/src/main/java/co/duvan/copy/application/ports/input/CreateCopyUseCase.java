package co.duvan.copy.application.ports.input;

import co.duvan.copy.domain.model.Copy;

public interface CreateCopyUseCase {

    Copy save(Copy copy);

}
