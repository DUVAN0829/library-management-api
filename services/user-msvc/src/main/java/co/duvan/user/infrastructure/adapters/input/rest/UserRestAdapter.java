package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.application.ports.input.CreateUserUseCase;
import co.duvan.user.application.ports.input.DeleteUserUseCase;
import co.duvan.user.application.ports.input.GetUserUseCase;
import co.duvan.user.application.ports.input.UpdateUserUseCase;
import co.duvan.user.domain.model.User;
import co.duvan.user.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import co.duvan.user.infrastructure.adapters.input.rest.model.request.UserRequest;
import co.duvan.user.infrastructure.adapters.input.rest.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestAdapter {

    private final UserRestMapper userRestMapper;
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping("/api/v1")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(userRestMapper.toUserResponseList(getUserUseCase.findAll()));

    }

    @GetMapping("/api/v1/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(userRestMapper.toUserResponse(getUserUseCase.findById(id)));

    }

    @PostMapping("/api/v1")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRestMapper.toUserResponse(createUserUseCase.save(userRestMapper.toUser(userRequest))));

    }

    @PutMapping("/api/v1/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(userRestMapper.toUserResponse(updateUserUseCase.update(id, userRestMapper.toUser(userRequest))));

    }

    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {

        deleteUserUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}