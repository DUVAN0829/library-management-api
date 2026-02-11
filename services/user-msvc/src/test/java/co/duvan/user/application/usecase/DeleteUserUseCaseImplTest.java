package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.exceptions.UserNotFoundException;
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
class DeleteUserUseCaseImplTest {

    @Mock
    private UserRepositoryPort repositoryPort;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void should_delete_user_successfully() {

        //* Arrange
        User user = new User();
        user.setUserId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(user));

        //* Act
        deleteUserUseCase.deleteById(1L);

        //* Assert
        verify(repositoryPort).findById(1L);
        verify(repositoryPort).deleteById(1L);

    }

    @Test
    void should_throw_exception_when_user_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act
        assertThrows(UserNotFoundException.class,
                () -> deleteUserUseCase.deleteById(1L));

        //* Assert
        verify(repositoryPort, never()).deleteById(1L);

    }

}


























