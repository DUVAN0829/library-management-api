package co.duvan.book.infrastructure.adapters.output.persistence;

import co.duvan.book.application.ports.output.BookRepositoryPort;
import co.duvan.book.domain.enums.Category;
import co.duvan.book.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class BookPersistenceAdapterIT {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17")
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
        Book bookSave = repositoryPort.save(book);

        //* Then
        assertNotNull(bookSave.getBookId());

    }

    @Test
    void should_find_book_by_id() {

        //* Given
        Book book = new Book();
        book.setTitle("Joyland");
        book.setDescription("A thriller book");
        book.setIsbn("ISBN-124");
        book.setAuthors(List.of("Stephen King"));
        book.setPublisher("Debolsillo");
        book.setCategory(Category.THRILLER);

        //* When
        Book bookSave = repositoryPort.save(book);
        Book found = repositoryPort.findById(bookSave.getBookId()).orElseThrow();

        //* Then
        assertNotNull(found);
        assertEquals(bookSave.getBookId(), found.getBookId());
        assertEquals(bookSave.getTitle(), found.getTitle());

    }

}













