package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.exceptions.BookNotFoundException;
import co.duvan.book.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetBookUseCaseImplTest {

    @Mock
    private BookRepositoryPort repositoryPort;

    @InjectMocks
    private GetBookUseCaseImpl getBookUseCase;

    @Test
    void should_return_book_when_exists() {

        //* Arrange
        Book book = new Book();
        book.setBookId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(book));

        //* Act
        Book result = getBookUseCase.findById(1L);

        //* Assert
        assertNotNull(result);
        assertNotNull(result.getBookId());

        verify(repositoryPort).findById(1L);

    }

    @Test
    void should_throw_exception_when_book_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act
        assertThrows(BookNotFoundException.class,
                () -> getBookUseCase.findById(1L));

        //* Assert
        verify(repositoryPort).findById(1L);

    }

    @Test
    void should_return_all_books_successfully() {

        //* Arrange
        Book bookA = new Book();
        bookA.setBookId(1L);

        Book bookB = new Book();
        bookB.setBookId(2L);

        when(repositoryPort.findAll()).thenReturn(List.of(bookA, bookB));

        //* Act
        List<Book> result = getBookUseCase.findAll();

        //* Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(repositoryPort).findAll();

    }

}