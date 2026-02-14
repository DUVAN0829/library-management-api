package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.enums.Status;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import co.duvan.copy.domain.model.Copy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCopyUseCaseImplTest {

    @Mock
    private CopyRepositoryPort repositoryPort;

    @InjectMocks
    private UpdateCopyUseCaseImpl updateCopyUseCase;

    @Test
    void should_update_copy_successfully() {

        //* Arrange
        Long copyId = 1L;

        Copy existingCopy = new Copy();
        existingCopy.setCopyId(copyId);
        existingCopy.setBookId(10L);
        existingCopy.setCode("OLD-CODE");
        existingCopy.setStatus(Status.AVAILABLE);

        Copy copyToUpdate = new Copy();
        copyToUpdate.setBookId(20L);
        copyToUpdate.setCode("NEW-CODE");
        copyToUpdate.setStatus(Status.LOANED);

        Copy savedCopy = new Copy();
        savedCopy.setCopyId(copyId);
        savedCopy.setBookId(20L);
        savedCopy.setCode("NEW-CODE");
        savedCopy.setStatus(Status.LOANED);

        when(repositoryPort.findById(copyId))
                .thenReturn(Optional.of(existingCopy));

        when(repositoryPort.save(existingCopy))
                .thenReturn(savedCopy);

        //* Act
        Copy result = updateCopyUseCase.update(copyId, copyToUpdate);

        //* Assert
        assertEquals("NEW-CODE", result.getCode());
        assertEquals(Status.LOANED, result.getStatus());
        assertEquals(20L, result.getBookId());

        verify(repositoryPort).findById(copyId);
        verify(repositoryPort).save(existingCopy);
    }

}