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

       Book book = new Book();

       Book saveBook = new Book();
       saveBook.setBookId(1L);

       when(repositoryPort.save(book)).thenReturn(saveBook);

       Book result = createBookUseCase.save(book);

       assertNotNull(result);
       assertNotNull(result.getBookId());

       verify(repositoryPort).save(book);

    }

}


