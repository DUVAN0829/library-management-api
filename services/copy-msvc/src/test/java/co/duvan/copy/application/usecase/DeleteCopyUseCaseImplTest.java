package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import co.duvan.copy.domain.model.Copy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCopyUseCaseImplTest {

    @Mock
    private CopyRepositoryPort repositoryPort;

    @InjectMocks
    private DeleteCopyUseCaseImpl deleteCopyUseCase;

    @Test
    void should_delete_copy_successfully() {

        //* Arrange
        Copy copy = new Copy();
        copy.setCopyId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(copy));

        //* Act
        deleteCopyUseCase.deleteById(1L);

        //* Assert
        verify(repositoryPort).deleteById(1L);
    }

    @Test
    void should_throw_exception_when_copy_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act & Assert
        assertThrows(CopyNotFoundException.class,
                () -> deleteCopyUseCase.deleteById(1L));

        verify(repositoryPort, never()).deleteById(1L);
    }

}