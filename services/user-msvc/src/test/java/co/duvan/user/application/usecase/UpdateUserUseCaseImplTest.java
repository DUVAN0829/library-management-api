package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {

    @Mock
    private UserRepositoryPort repositoryPort;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void should_update_user_successfully() {

        //* Arrange
        Long bookId = 1L;

        User exitingUser = new User();
        exitingUser.setUserId(bookId);
        exitingUser.setPhoneNumber("318-098-631");

        User usertoUpdate = new User();
        usertoUpdate.setPhoneNumber("319-765-632");

        User savedUser = new User();
        savedUser.setUserId(bookId);
        savedUser.setPhoneNumber("319-765-632");

        when(repositoryPort.findById(bookId)).thenReturn(Optional.of(exitingUser));
        when(repositoryPort.save(usertoUpdate)).thenReturn(savedUser);

        //* Act
        User result = updateUserUseCase.update(bookId, usertoUpdate);

        //* Assert
        assertNotNull(result);
        assertEquals("319-765-632", result.getPhoneNumber());

    }

}





