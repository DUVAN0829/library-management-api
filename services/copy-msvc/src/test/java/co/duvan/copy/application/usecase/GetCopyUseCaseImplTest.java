package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import co.duvan.copy.domain.model.Copy;
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
class GetCopyUseCaseImplTest {

    @Mock
    private CopyRepositoryPort repositoryPort;

    @InjectMocks
    private GetCopyUseCaseImpl getCopyUseCase;

    @Test
    void should_return_copy_when_exists() {

        //* Arrange
        Copy copy = new Copy();
        copy.setCopyId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(copy));

        //* Act
        Copy result = getCopyUseCase.findById(1L);

        //* Assert
        assertNotNull(result);
        assertEquals(1L, result.getCopyId());

        verify(repositoryPort).findById(1L);
    }

    @Test
    void should_throw_exception_when_copy_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act + Assert
        assertThrows(CopyNotFoundException.class,
                () -> getCopyUseCase.findById(1L));

        verify(repositoryPort).findById(1L);
    }

    @Test
    void should_return_all_copies_successfully() {

        //* Arrange
        Copy copyA = new Copy();
        copyA.setCopyId(1L);

        Copy copyB = new Copy();
        copyB.setCopyId(2L);

        when(repositoryPort.findAll()).thenReturn(List.of(copyA, copyB));

        //* Act
        List<Copy> result = getCopyUseCase.findAll();

        //* Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(repositoryPort).findAll();
    }

}