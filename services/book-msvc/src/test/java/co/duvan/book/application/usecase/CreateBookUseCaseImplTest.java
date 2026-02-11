package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

       Book exitingBook = new Book();
       exitingBook.setBookId(1L);

       when(repositoryPort.save(book)).thenReturn(exitingBook);

       //* Act
       Book result = createBookUseCase.save(book);

       //* Assert
       assertNotNull(result);
       assertNotNull(result.getBookId());

       verify(repositoryPort).save(book);

    }

}


