package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.enums.Category;
import co.duvan.book.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateBookUseCaseImplTest {

    @Mock
    private BookRepositoryPort repositoryPort;

    @InjectMocks
    private CreateBookUseCaseImpl createBookUseCase;

    @Test
    void should_create_book_successfully() {

        //* Arrange
        Book book = new Book();
        book.setTitle("Joyland");
        book.setDescription("A young college student takes a summer job at an amusement park, which hides a dark secret.");
        book.setAuthors(List.of("Stephen King"));
        book.setCategory(Category.THRILLER);
        book.setPublisher("Scribner");

        Book bookDB = new Book();
        bookDB.setBookId(1L);
        bookDB.setTitle(book.getTitle());
        bookDB.setIsbn("ISBN-" + bookDB.getBookId());
        bookDB.setDescription(book.getDescription());
        bookDB.setAuthors(book.getAuthors());
        bookDB.setCategory(book.getCategory());
        bookDB.setPublisher(book.getPublisher());

        when(repositoryPort.save(book)).thenReturn(bookDB);

        //* Act
        Book result = createBookUseCase.save(book);

        //* Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getBookId()),
                () -> assertEquals("Joyland", result.getTitle()),
                () -> assertEquals("A young college student takes a summer job at an amusement park, which hides a dark secret.", result.getDescription()),
                () -> assertEquals("ISBN-" + result.getBookId(), result.getIsbn()),
                () -> assertNotNull(result.getAuthors()),
                () -> assertEquals(Category.THRILLER, result.getCategory()),
                () -> assertEquals("Scribner", result.getPublisher())
        );

        verify(repositoryPort, times(1)).save(book);

    }

}


