package co.duvan.prestamo.infrastructure.adapters.output.persistence;

import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.enums.Status;
import co.duvan.prestamo.domain.model.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class LoanPersistenceAdapterTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine")
                    .withDatabaseName("prestamos")
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
    private LoanRepositoryPort repositoryPort;

    @Test
    void should_save_loan() {

        //* Given
        Loan loan = new Loan(
                null,
                1L,
                10L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.ACTIVE
        );

        //* When
        Loan savedLoan = repositoryPort.save(loan);

        //* Then
        assertNotNull(savedLoan.getLoanId());
    }

}