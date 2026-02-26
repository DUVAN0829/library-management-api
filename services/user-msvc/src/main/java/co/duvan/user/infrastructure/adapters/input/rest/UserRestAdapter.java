package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.application.ports.input.CreateUserUseCase;
import co.duvan.user.application.ports.input.DeleteUserUseCase;
import co.duvan.user.application.ports.input.GetUserUseCase;
import co.duvan.user.application.ports.input.UpdateUserUseCase;
import co.duvan.user.infrastructure.adapters.input.rest.documentation.DefaultApiErrors;
import co.duvan.user.infrastructure.adapters.input.rest.documentation.ValidationApiError;
import co.duvan.user.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import co.duvan.user.infrastructure.adapters.input.rest.model.request.UserRequest;
import co.duvan.user.infrastructure.adapters.input.rest.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "Users API", description = "Operations basic related users (CRUD)")
public class UserRestAdapter {

    private final UserRestMapper userRestMapper;
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List all users")
    @DefaultApiErrors
    @GetMapping("/api/v1")
    public ResponseEntity<List<UserResponse>> findAll() {

        return ResponseEntity.ok(userRestMapper.toUserResponseList(getUserUseCase.findAll()));

    }

    @Operation(summary = "Get user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DefaultApiErrors
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwner(authentication, #id)")
    @GetMapping("/api/v1/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(userRestMapper.toUserResponse(getUserUseCase.findById(id)));

    }

    @Operation(summary = "Get userId by keycloakId")
    @DefaultApiErrors
    @PreAuthorize("hasRole('MEMBER') or hasRole('LIBRARIAN') or hasRole('ADMIN')")
    @GetMapping("/api/v1/internal/keycloak/{keycloakId}")
    public ResponseEntity<Long> findUserIdByKeycloakId(@PathVariable String keycloakId) {
        return ResponseEntity.ok(getUserUseCase.findUserIdByKeycloakId(keycloakId));
    }

    @Operation(summary = "Create user")
    @ApiResponse(responseCode = "201", description = "User created")
    @DefaultApiErrors
    @ValidationApiError
    @PostMapping("/api/v1")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRestMapper.toUserResponse(createUserUseCase.save(userRestMapper.toUser(userRequest))));

    }

    @Operation(summary = "Update user")
    @ApiResponse(responseCode = "200", description = "User updated")
    @DefaultApiErrors
    @ValidationApiError
    @PutMapping("/api/v1/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(userRestMapper.toUserResponse(updateUserUseCase.update(id, userRestMapper.toUser(userRequest))));

    }

    @Operation(summary = "Delete user")
    @ApiResponse(responseCode = "204", description = "User deleted")
    @DefaultApiErrors
    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        deleteUserUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}