package co.duvan.copy.infrastructure.adapters.input.rest;

import co.duvan.copy.application.ports.input.CreateCopyUseCase;
import co.duvan.copy.application.ports.input.DeleteCopyUseCase;
import co.duvan.copy.application.ports.input.GetCopyUseCase;
import co.duvan.copy.application.ports.input.UpdateCopyUseCase;
import co.duvan.copy.domain.model.Copy;
import co.duvan.copy.infrastructure.adapters.input.rest.mapper.CopyRestMapper;
import co.duvan.copy.infrastructure.adapters.input.rest.model.request.CopyRequest;
import co.duvan.copy.infrastructure.adapters.input.rest.model.response.CopyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/copies")
public class CopyRestAdapter {

    private final CopyRestMapper copyRestMapper;
    private final GetCopyUseCase getCopyUseCase;
    private final CreateCopyUseCase createCopyUseCase;
    private final UpdateCopyUseCase updateCopyUseCase;
    private final DeleteCopyUseCase deleteCopyUseCase;

    @GetMapping("/api/v1/{id}")
    public ResponseEntity<CopyResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(copyRestMapper.toCopyResponse(getCopyUseCase.findById(id)));

    }

    @GetMapping("/api/v1")
    public ResponseEntity<List<CopyResponse>> findAll() {

        return ResponseEntity.ok(copyRestMapper.toCopyResponseList((List<Copy>) getCopyUseCase.findAll()));

    }

    @PostMapping("/api/v1")
    public ResponseEntity<CopyResponse> save(@Valid @RequestBody CopyRequest copyRequest) {

        return ResponseEntity.ok(copyRestMapper.toCopyResponse(createCopyUseCase.save(copyRestMapper.toCopy(copyRequest))));

    }

    @PutMapping("/api/v1/{id}")
    public ResponseEntity<CopyResponse> update(@PathVariable Long id, @Valid @RequestBody CopyRequest copyRequest) {

        return ResponseEntity.ok(copyRestMapper.toCopyResponse(updateCopyUseCase.update(id, copyRestMapper.toCopy(copyRequest))));

    }

    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        deleteCopyUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}