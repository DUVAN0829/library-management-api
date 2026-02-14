package co.duvan.loan.infrastructure.adapters.output.persistence;

import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.domain.enums.Status;
import co.duvan.loan.domain.model.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class LoanPersistenceAdapterIT {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine")
                    .withDatabaseName("loans")
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

    @Test
    void should_find_loan_by_id() {

        //* Given
        Loan loan = new Loan(
                null,
                2L,
                20L,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                null,
                Status.ACTIVE
        );

        Loan savedLoan = repositoryPort.save(loan);

        //* When
        Loan foundLoan = repositoryPort.findById(savedLoan.getLoanId()).orElseThrow();

        //* Then
        assertNotNull(foundLoan);
        assertEquals(savedLoan.getLoanId(), foundLoan.getLoanId());
    }

    @Test
    void should_find_all_loans() {

        //* Given
        Loan loanA = new Loan(
                null,
                3L,
                30L,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                null,
                Status.ACTIVE
        );

        Loan loanB = new Loan(
                null,
                4L,
                40L,
                LocalDate.now(),
                LocalDate.now().plusDays(8),
                null,
                Status.ACTIVE
        );

        repositoryPort.save(loanA);
        repositoryPort.save(loanB);

        //* When
        List<Loan> loans = repositoryPort.findAll();

        //* Then
        assertFalse(loans.isEmpty());
        assertTrue(loans.size() >= 2);
    }

    @Test
    void should_update_loan() {

        //* Given
        Loan loan = new Loan(
                null,
                5L,
                50L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.ACTIVE
        );

        Loan savedLoan = repositoryPort.save(loan);

        //* When
        savedLoan.setLoanStatus(Status.RETURNED);
        savedLoan.setReturnDate(LocalDate.now());

        Loan updatedLoan = repositoryPort.save(savedLoan);

        //* Then
        assertEquals(Status.RETURNED, updatedLoan.getLoanStatus());
        assertNotNull(updatedLoan.getReturnDate());
    }

    @Test
    void should_delete_loan() {

        //* Given
        Loan loan = new Loan(
                null,
                6L,
                60L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.ACTIVE
        );

        Loan savedLoan = repositoryPort.save(loan);

        //* When
        repositoryPort.deleteById(savedLoan.getLoanId());

        //* Then
        Optional<Loan> deletedLoan = repositoryPort.findById(savedLoan.getLoanId());
        assertTrue(deletedLoan.isEmpty());
    }

}