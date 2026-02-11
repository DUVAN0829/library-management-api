package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserRepositoryPort repositoryPort;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void should_create_user_successfully() {

        //* Arrange
        User user = new User();

        User exitingUser = new User();
        exitingUser.setUserId(1L);

        when(repositoryPort.save(user)).thenReturn(exitingUser);

        //* Act
        User result = createUserUseCase.save(user);

        //* Assert
        assertNotNull(result);
        assertNotNull(result.getUserId());

        verify(repositoryPort).save(user);

    }

}





























