package co.duvan.copy.infrastructure.adapters.output.persistence;

import co.duvan.copy.application.ports.output.CopyRepositoryPort;
import co.duvan.copy.domain.enums.Status;
import co.duvan.copy.domain.model.Copy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class CopyPersistenceAdapterIT {

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.3")
                    .withDatabaseName("copies")
                    .withUsername("root")
                    .withPassword("12345");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private CopyRepositoryPort repositoryPort;

    @Test
    void should_save_copy() {

        //* Given
        Copy copy = Copy.builder()
                .bookId(1L)
                .code("CP-001")
                .status(Status.AVAILABLE)
                .build();

        //* When
        Copy savedCopy = repositoryPort.save(copy);

        //* Then
        assertNotNull(savedCopy);
        assertNotNull(savedCopy.getCopyId());
    }

    @Test
    void should_find_copy_by_id() {

        //* Given
        Copy copy = Copy.builder()
                .bookId(2L)
                .code("CP-002")
                .status(Status.AVAILABLE)
                .build();

        Copy savedCopy = repositoryPort.save(copy);

        //* When
        Copy result = repositoryPort.findById(savedCopy.getCopyId()).orElseThrow();

        //* Then
        assertNotNull(result);
        assertEquals(savedCopy.getCopyId(), result.getCopyId());
    }

    @Test
    void should_find_all_copies() {

        //* Given
        repositoryPort.save(Copy.builder().bookId(1L).code("CP-003").status(Status.AVAILABLE).build());
        repositoryPort.save(Copy.builder().bookId(1L).code("CP-004").status(Status.LOANED).build());

        //* When
        List<Copy> result = repositoryPort.findAll();

        //* Then
        assertFalse(result.isEmpty());
        assertTrue(result.size() >= 2);
    }

}