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

        Copy copy = Copy.builder()
                .bookId(1L)
                .code("CP-001")
                .status(Status.AVAILABLE)
                .build();

        Copy savedCopy = repositoryPort.save(copy);

        assertNotNull(savedCopy);
        assertNotNull(savedCopy.getCopyId());
    }

}