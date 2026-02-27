package co.duvan.copy.infrastructure.adapters.input.rest;

import co.duvan.copy.application.ports.input.CreateCopyUseCase;
import co.duvan.copy.application.ports.input.DeleteCopyUseCase;
import co.duvan.copy.application.ports.input.GetCopyUseCase;
import co.duvan.copy.application.ports.input.UpdateCopyUseCase;
import co.duvan.copy.domain.model.Copy;
import co.duvan.copy.infrastructure.adapters.input.rest.documentation.DefaultApiErrors;
import co.duvan.copy.infrastructure.adapters.input.rest.documentation.ValidationApiError;
import co.duvan.copy.infrastructure.adapters.input.rest.mapper.CopyRestMapper;
import co.duvan.copy.infrastructure.adapters.input.rest.model.request.CopyRequest;
import co.duvan.copy.infrastructure.adapters.input.rest.model.response.CopyDetailResponse;
import co.duvan.copy.infrastructure.adapters.input.rest.model.response.CopyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/copies")
@Tag(name = "Copies API", description = "Operations basic related copies (CRUD)")
public class CopyRestAdapter {

    private final CopyRestMapper copyRestMapper;
    private final GetCopyUseCase getCopyUseCase;
    private final CreateCopyUseCase createCopyUseCase;
    private final UpdateCopyUseCase updateCopyUseCase;
    private final DeleteCopyUseCase deleteCopyUseCase;

    @Operation(summary = "Get copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Copy found"),
            @ApiResponse(responseCode = "404", description = "Copy not found")
    })
    @DefaultApiErrors
    @GetMapping("/api/v1/{id}")
    public ResponseEntity<CopyDetailResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(copyRestMapper.toCopyDetailResponse(getCopyUseCase.findById(id)));

    }

    @Operation(summary = "Get all copies")
    @ApiResponse(responseCode = "200", description = "List all copies")
    @DefaultApiErrors
    @GetMapping("/api/v1")
    public ResponseEntity<List<CopyResponse>> findAll() {

        return ResponseEntity.ok(copyRestMapper.toCopyResponseList(getCopyUseCase.findAll()));

    }

    @Operation(summary = "Create copy")
    @ApiResponse(responseCode = "201", description = "Copy created")
    @DefaultApiErrors
    @ValidationApiError
    @PostMapping("/api/v1")
    public ResponseEntity<CopyResponse> save(@Valid @RequestBody CopyRequest copyRequest) {

        return ResponseEntity.ok(copyRestMapper.toCopyResponse(createCopyUseCase.save(copyRestMapper.toCopy(copyRequest))));

    }

    @Operation(summary = "Update copy")
    @ApiResponse(responseCode = "200", description = "Copy updated")
    @DefaultApiErrors
    @ValidationApiError
    @PutMapping("/api/v1/{id}")
    public ResponseEntity<CopyResponse> update(@PathVariable Long id, @Valid @RequestBody CopyRequest copyRequest) {

        return ResponseEntity.ok(copyRestMapper.toCopyResponse(updateCopyUseCase.update(id, copyRestMapper.toCopy(copyRequest))));

    }

    @Operation(summary = "Delete copy")
    @ApiResponse(responseCode = "204", description = "Copy   deleted")
    @DefaultApiErrors
    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        deleteCopyUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}