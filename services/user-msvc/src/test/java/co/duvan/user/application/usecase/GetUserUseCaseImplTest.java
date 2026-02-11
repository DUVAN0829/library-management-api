package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import co.duvan.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {

    @Mock
    private UserRepositoryPort repositoryPort;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void should_return_user_when_exists() {

        //* Arrange
        User user = new User();
        user.setUserId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(user));

        //* Act
        User result = getUserUseCase.findById(1L);

        //* Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());

        verify(repositoryPort).findById(1L);

    }

    @Test
    void should_throw_exception_when_user_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act
        assertThrows(UserNotFoundException.class,
                () -> getUserUseCase.findById(1L));

        //* Assert
        verify(repositoryPort).findById(1L);

    }

    @Test
    void should_return_all_users_successfully() {

        //* Arrange
        User userA = new User();
        userA.setUserId(1L);

        User userB = new User();
        userB.setUserId(2L);

        when(repositoryPort.findAll()).thenReturn(List.of(userA, userB));

        //* Act
        List<User> userList = getUserUseCase.findAll();

        //* Assert
        assertNotNull(userList);
        assertEquals(2, userList.size());

        verify(repositoryPort).findAll();

    }

}
























