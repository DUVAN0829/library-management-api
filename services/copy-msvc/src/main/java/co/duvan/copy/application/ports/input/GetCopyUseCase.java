package co.duvan.copy.application.ports.input;

import co.duvan.copy.application.ports.output.dto.CopyDetailResult;
import co.duvan.copy.domain.model.Copy;

import java.util.List;

public interface GetCopyUseCase {

    CopyDetailResult findById(Long id);

    List<Copy> findAll();

}