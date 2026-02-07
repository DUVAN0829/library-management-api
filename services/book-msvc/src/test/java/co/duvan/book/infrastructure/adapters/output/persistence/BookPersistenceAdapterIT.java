package co.duvan.book.infrastructure.adapters.output.persistence;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.enums.Category;
import co.duvan.book.domain.model.Book;
import co.duvan.book.infrastructure.adapters.output.persistence.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class BookPersistenceAdapterIT {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine")
                    .withDatabaseName("books")
                    .withUsername("postgres")
                    .withPassword("12345");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private BookRepositoryPort repositoryPort;

    @Test
    void should_save_book() {

        //* Given
        Book book = new Book();
        book.setTitle("Joyland");
        book.setDescription("A thriller book");
        book.setIsbn("ISBN-123");
        book.setAuthors(List.of("Stephen King"));
        book.setPublisher("Debolsillo");
        book.setCategory(Category.THRILLER);

        //* When
        Book savedBook = repositoryPort.save(book);

        //* Then
        assertNotNull(savedBook.getBookId());

    }

    @Test
    void should_find_book_by_id() {

        //* Given
        Book book = new Book();
        book.setTitle("The Kite Runner");
        book.setDescription("A moving story of friendship and redemption");
        book.setIsbn("ISBN-978");
        book.setAuthors(List.of("Khaled Hosseini"));
        book.setPublisher("Riverhead Books");
        book.setCategory(Category.DRAMA);

        //* When
        Book savedBook = repositoryPort.save(book);
        Book foundBook = repositoryPort.findById(savedBook.getBookId()).orElseThrow();

        //* Then
        assertNotNull(foundBook);
        assertEquals(savedBook.getBookId(), foundBook.getBookId());

    }

    @Test
    void should_find_all_books() {

        //* Give
        Book bookA = new Book();
        bookA.setTitle("Clean Code");
        bookA.setDescription("A practical guide to writing clean, readable, and maintainable software.");
        bookA.setPublisher("Addison-Wesley");
        bookA.setIsbn("ISBN-456");
        bookA.setCategory(Category.PROGRAMMING);
        bookA.setAuthors(List.of("Robert C. Martin", "Prentice Hall", "Pearson"));

        Book bookB = new Book();
        bookB.setTitle("Clean Code (Second Edition)");
        bookB.setDescription("Updated guidelines and best practices for writing clean and maintainable code.");
        bookB.setPublisher("Addison-Wesley");
        bookB.setIsbn("ISBN-874");
        bookB.setCategory(Category.PROGRAMMING);
        bookB.setAuthors(List.of("Robert C. Martin", "Prentice Hall", "Pearson"));

        //* When
        repositoryPort.save(bookA);
        repositoryPort.save(bookB);

        List<Book> listBook = repositoryPort.findAll();

        //* Then
        assertFalse(listBook.isEmpty());
        assertTrue(listBook.size() >= 2);

    }

    @Test
    void should_update_book() {

        //* Given
        Book book = new Book();
        book.setTitle("Sapiens: A Brief History of Humankind");
        book.setDescription("History of humankind");
        book.setIsbn("ISBN-609");
        book.setAuthors(List.of("Yuval Noah Harari"));
        book.setPublisher("Harper");
        book.setCategory(Category.HISTORY);

        //* When
        Book savedBook = repositoryPort.save(book);

        savedBook.setPublisher("Vintage");

        Book updatedBook = repositoryPort.save(savedBook);

        //* Then
        assertEquals("Vintage", updatedBook.getPublisher());

    }

    @Test
    void should_delete_book() {

        //* Given
        Book book = new Book();
        book.setTitle("Pride and Prejudice");
        book.setDescription("A classic romance about love, misunderstandings, and social class");
        book.setIsbn("ISBN-101");
        book.setAuthors(List.of("Jane Austen"));
        book.setPublisher("T. Egerton");
        book.setCategory(Category.ROMANCE);

        //* When
        Book savedBook = repositoryPort.save(book);

        repositoryPort.deleteById(savedBook.getBookId());

       Optional<Book> deletedBook = repositoryPort.findById(savedBook.getBookId());

       assertTrue(deletedBook.isEmpty());

    }

    @Test
    void should_fail_when_isbn_is_duplicated() {

        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setIsbn("ISBN-999");
        book1.setCategory(Category.THRILLER);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setIsbn("ISBN-999");
        book2.setCategory(Category.DRAMA);

        repositoryPort.save(book1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repositoryPort.save(book2);
        });

    }

}


























