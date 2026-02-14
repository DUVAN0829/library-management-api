package co.duvan.copy.application.usecase;

import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.enums.Status;
import co.duvan.copy.domain.model.Copy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCopyUseCaseImplTest {

    @Mock
    private CopyRepositoryPort repositoryPort;

    @InjectMocks
    private CreateCopyUseCaseImpl createCopyUseCase;

    @Test
    void should_create_copy_successfully() {

        //* Arrange
        Copy copy = new Copy();
        copy.setBookId(1L);
        copy.setCode("CP-001");
        copy.setStatus(Status.AVAILABLE);

        Copy savedCopy = new Copy();
        savedCopy.setCopyId(10L);
        savedCopy.setBookId(1L);
        savedCopy.setCode("CP-001");
        savedCopy.setStatus(Status.AVAILABLE);

        when(repositoryPort.save(copy)).thenReturn(savedCopy);

        //* Act
        Copy result = createCopyUseCase.save(copy);

        //* Assert
        assertNotNull(result);
        assertNotNull(result.getCopyId());
        assertEquals(10L, result.getCopyId());

        verify(repositoryPort).save(copy);
    }

}