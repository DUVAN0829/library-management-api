package co.duvan.book.application.usecase;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateBookUseCaseImplTest {

    @Mock
    private BookRepositoryPort repositoryPort;

    @InjectMocks
    private UpdateBookUseCaseImpl updateBookUseCase;

    @Test
    void should_update_book_successfully() {

       //* Arrange
       Long bookId= 1L;

       Book existingBook = new Book();
       existingBook.setBookId(bookId);
       existingBook.setTitle("Old title");

       Book bookToUpdate = new Book();
       bookToUpdate.setTitle("New title");

       Book saveBook = new Book();
       saveBook.setBookId(bookId);
       saveBook.setTitle("New title");

       when(repositoryPort.findById(bookId))
               .thenReturn(Optional.of(existingBook));

       when(repositoryPort.save(any(Book.class)))
               .thenReturn(saveBook);

       //* Act
       Book result = updateBookUseCase.update(bookId, bookToUpdate);

       //* Assert
       assertEquals("New title", result.getTitle());

       verify(repositoryPort).findById(bookId);
       verify(repositoryPort).save(existingBook);

    }

}















