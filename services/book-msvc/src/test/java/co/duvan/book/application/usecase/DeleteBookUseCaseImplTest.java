package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.exceptions.BookNotFoundException;
import co.duvan.book.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteBookUseCaseImplTest {

    @Mock
    private BookRepositoryPort repositoryPort;

    @InjectMocks
    private DeleteBookUseCaseImpl deleteBookUseCase;

    @Test
    void should_delete_book_successfully() {

        //* Arrange
        Book book = new Book();
        book.setBookId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(book));

        //* Act
        deleteBookUseCase.deleteById(1L);

        //* Assert
        verify(repositoryPort).deleteById(1L);

    }

    @Test
    void should_throw_exception_when_book_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act
        assertThrows(BookNotFoundException.class,
                () -> deleteBookUseCase.deleteById(1L));

        //* Assert
        verify(repositoryPort, never()).deleteById(1L);

    }

}



























