package co.duvan.copy.application.ports.input;

import co.duvan.copy.domain.model.Copy;

public interface UpdateCopyUseCase {

    Copy update(Long id, Copy copy);

    void updateStatus(Long copyId, String status);

}